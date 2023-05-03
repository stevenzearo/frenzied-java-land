package app.site.service.messagehandler;

import app.ichat.api.ChatWebService;
import app.ichat.api.chat.ChatRequest;
import app.ichat.api.chat.ChatResponse;
import app.site.config.ResourceConfig;
import app.site.model.ChatCache;
import app.site.model.common.MessageType;
import app.site.model.receive.ReceivedMessage;
import app.site.model.receive.normal.TextMessage;
import app.site.model.reply.Article;
import app.site.model.reply.ArticleItem;
import app.site.model.reply.ReplyingMessage;
import app.site.model.reply.ReplyingMessageBuilder;
import app.site.service.ChatCacheService;
import app.web.error.ConflictException;
import app.web.error.WebException;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author simple
 */
@Component
public final class TextMessageHandler extends AbstractMessageHandler<TextMessage> {
    private static final String TURING_BOT_NAME = "图灵儿";
    private static final MessageType handledMsgType = MessageType.TEXT;
    private static final List<String> CHAT_BOT_NAMES = ReplyingContentUtil.getChatBotNames();
    private static final List<String> UNSUPPORTED_REPLIES = ReplyingContentUtil.getUnsupportedMsgReplies();
    private final Logger logger = LoggerFactory.getLogger(TextMessageHandler.class);

    @Autowired(required = false)
    ChatWebService chatWebService;
    @Autowired
    ChatCacheService chatCacheService;
    @Autowired
    ResourceConfig resourceConfig;

    @Override
    public ReplyingMessage innerHandle(TextMessage message) {
        return replyText(message);
    }

    @Override
    protected void checkMessage(TextMessage message) {
        if (message.msgType != handledMsgType || null == message.content) {
            throw new ConflictException(String.format("Invalid text message type=%s to handle=%s", message.getClass().getName(), this.getClass().getName()));
        }
    }

    private ReplyingMessage replyText(TextMessage message) {
        if (message.content.contains("新闻") || message.content.toLowerCase(Locale.ROOT).contains("news")) {
            return replyNews(message);
        } else {
            return replyChat(message);
        }
    }

    private ReplyingMessage replyChat(TextMessage message) {
        return new ReplyingMessageBuilder(message).text(transferToAnswer(message));
    }

    private ReplyingMessage replyNews(ReceivedMessage message) {
        ArticleItem articleItem = new ArticleItem();
        articleItem.title = "Hello, world!";
        articleItem.description = "Hello, world!";
        articleItem.picUrl = resourceConfig.resourcePrefix + "/img/a_small.jpg";
        articleItem.url = resourceConfig.resourcePrefix + "/index.html";
        Article article = new Article();
        article.items = List.of(articleItem);
        return new ReplyingMessageBuilder(message).articles(article);
    }

    private String transferToAnswer(TextMessage message) {
        ChatResponse chatResponse;
        try {
            chatResponse = chat(message);
        } catch (WebException e) {
            logger.error(e.getMessage(), e);
            return ReplyingContentUtil.randomChoice(UNSUPPORTED_REPLIES);
        }
        String answer = chatResponse.answer;
        if (answer.contains(TURING_BOT_NAME)) {
            answer = answer.replace(TURING_BOT_NAME, ReplyingContentUtil.randomChoice(CHAT_BOT_NAMES));
        }
        boolean isTextReply = chatResponse.items.stream().allMatch(i -> ChatResponse.ItemType.TEXT == i.type);
        if (!isTextReply) {
            answer = ReplyingContentUtil.randomChoice(UNSUPPORTED_REPLIES);
        }
        return answer;
    }

    private ChatResponse chat(TextMessage message) {
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.text = message.content;
        String fromUserName = message.fromUserName;
        ChatCache chatCache = chatCacheService.connect(fromUserName);
        String chatId = Integer.toString((chatCache.userId + chatCache.salt).hashCode());
        Response<ChatResponse> response = chatWebService.chat(chatId, chatRequest);
        return ResponseHelper.fetchDataWithException(response);
    }
}
