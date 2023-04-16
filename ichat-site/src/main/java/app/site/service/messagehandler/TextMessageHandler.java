package app.site.service.messagehandler;

import app.ichat.api.ChatWebService;
import app.ichat.api.chat.ChatRequest;
import app.ichat.api.chat.ChatResponse;
import app.site.model.ChatCache;
import app.site.model.MessageType;
import app.site.model.ReceivedMessage;
import app.site.model.ReplyingMessage;
import app.site.service.ChatCacheService;
import app.web.error.WebException;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author simple
 */
@Service
public class TextMessageHandler implements MessageHandler {
    private static final String TURING_BOT_NAME = "图灵儿";
    private static final List<String> CHAT_BOT_NAMES = getChatBotNames();
    private static final List<String> UNSUPPORTED_REPLIES = getUnsupportedMsgReplies();

    private final Logger logger = LoggerFactory.getLogger(TextMessageHandler.class);

    @Autowired(required = false)
    ChatWebService chatWebService;
    @Autowired
    ChatCacheService chatCacheService;

    @Override
    public ReplyingMessage handle(ReceivedMessage message) {
        ReplyingMessage replyingMessage = basic(message);
        replyingMessage.msgType = MessageType.TEXT;
        replyingMessage.content = transferToAnswer(message);
        return replyingMessage;
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
