package app.site.service;

import app.ichat.api.ChatWebService;
import app.ichat.api.chat.ChatRequest;
import app.ichat.api.chat.ChatResponse;
import app.site.model.ChatCache;
import app.site.model.Image;
import app.site.model.MessageType;
import app.site.model.ReceivedMessage;
import app.site.model.ReplyingMessage;
import app.web.error.WebException;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class ChatService {
    private static final String TURING_BOT_NAME = "图灵儿";
    private static final List<String> CHAT_BOT_NAMES = getChatBotNames();
    private static final List<String> UNSUPPORTED_REPLIES = getUnsupportedMsgReplies();
    private final Logger logger = LoggerFactory.getLogger(ChatService.class);
    @Autowired(required = false)
    ChatWebService chatWebService;
    @Autowired
    ChatCacheService chatCacheService;

    public byte[] chat(String body) {
        ReceivedMessage message = receiveMessage(body);
        ReplyingMessage replyingMessage = new ReplyingMessage();
        replyingMessage.fromUserName = message.toUserName;
        replyingMessage.toUserName = message.fromUserName;
        replyingMessage.createTime = System.currentTimeMillis();
        replyingMessage.msgId = UUID.randomUUID().toString();
        if (MessageType.TEXT == message.msgType) {
            replyingMessage.msgType = MessageType.TEXT;
            replyingMessage.content = transferToAnswer(message);
        } else if (MessageType.IMAGE == message.msgType) {
            Image image = new Image();
            image.mediaId = message.mediaId;
            replyingMessage.media = image;
        } else {
            throw new WebException("Unsupported media type");
        }
        return replyMessage(replyingMessage);
    }

    private String transferToAnswer(ReceivedMessage message) {
        ChatResponse chatResponse;
        try {
            chatResponse = chat(message);
        } catch (WebException e) {
            logger.error(e.getMessage(), e);
            return randomChoice(UNSUPPORTED_REPLIES);
        }
        String answer = chatResponse.answer;
        if (answer.contains(TURING_BOT_NAME)) {
            answer = answer.replace(TURING_BOT_NAME, randomChoice(CHAT_BOT_NAMES));
        }
        boolean isTextReply = chatResponse.items.stream().allMatch(i -> ChatResponse.ItemType.TEXT == i.type);
        if (!isTextReply) {
            answer = randomChoice(UNSUPPORTED_REPLIES);
        }
        return answer;
    }

    private ChatResponse chat(ReceivedMessage message) {
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.text = message.content;
        String fromUserName = message.fromUserName;
        ChatCache chatCache = chatCacheService.connect(fromUserName);
        String chatId = (chatCache.userId + chatCache.salt).hashCode() + "";
        Response<ChatResponse> response = chatWebService.chat(chatId, chatRequest);
        return ResponseHelper.fetchDataWithException(response);
    }

    private ReceivedMessage receiveMessage(String body) {
        ReceivedMessage message;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ReceivedMessage.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            byteArrayInputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
            message = (ReceivedMessage) unmarshaller.unmarshal(byteArrayInputStream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    logger.warn("Failed to close ByteArrayInputStream", e);
                }
            }
        }
        return message;
    }

    private byte[] replyMessage(ReplyingMessage replyingMessage) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ReplyingMessage.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            byteArrayOutputStream = new ByteArrayOutputStream(1024 * 1000); // maxSize = 1kb
            marshaller.marshal(replyingMessage, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    logger.warn("Failed to close ByteArrayOutputStream", e);
                }
            }
        }
    }

    private static List<String> getUnsupportedMsgReplies() {
        ArrayList<String> replies = new ArrayList<>();
        replies.add("这个算是知识盲区了...");
        replies.add("这个咱不会...");
        replies.add("要不，咱们聊点别的？");
        replies.add("我觉得我们都应该冷静一下...");
        replies.add("这个咱不会，但是可以学，嘻嘻~");
        return replies;
    }

    private static List<String> getChatBotNames() {
        ArrayList<String> chatBotNames = new ArrayList<>();
        chatBotNames.add("小野");
        chatBotNames.add("小Z");
        chatBotNames.add("小L");
        chatBotNames.add("H");
        chatBotNames.add("MR.野");
        chatBotNames.add("MR.Z");
        chatBotNames.add("MR.L");
        chatBotNames.add("MR.H");
        return chatBotNames;
    }

    private static String randomChoice(List<String> choices) {
        if (choices.isEmpty()) return "";
        Random random = new Random();
        int i = random.nextInt(choices.size());
        return choices.get(i);
    }
}
