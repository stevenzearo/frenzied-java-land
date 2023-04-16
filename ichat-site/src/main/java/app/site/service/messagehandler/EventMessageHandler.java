package app.site.service.messagehandler;

import app.site.model.EventType;
import app.site.model.MessageType;
import app.site.model.ReceivedMessage;
import app.site.model.ReplyingMessage;
import app.web.error.WebException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author simple
 */
@Service
public class EventMessageHandler implements MessageHandler {
    private final Set<String> cache = new HashSet<>();
    private final Set<String> subscribers = new HashSet<>();
    private final Logger logger = LoggerFactory.getLogger(EventMessageHandler.class);

    @Override
    public ReplyingMessage handle(ReceivedMessage message) {
        checkRequested(message);
        if (message.event == EventType.SUBSCRIBE) {
            subscribers.add(message.fromUserName);
            return welcomeMessage(message);
        }
        subscribers.remove(message.fromUserName);
        return null;
    }

    private ReplyingMessage welcomeMessage(ReceivedMessage message) {
        ReplyingMessage replyingMessage = basic(message);
        replyingMessage.msgType = MessageType.TEXT;
        replyingMessage.content = new String("你好，欢迎关注野生程序员聚集地".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        return replyingMessage;
    }

    private void checkRequested(ReceivedMessage message) {
        String requestId = String.format("%s-%s", message.fromUserName, message.createTime);
        if (cache.contains(requestId)) {
            throw new WebException("duplicated request");
        }
        cache.add(requestId);
    }

    @Scheduled(cron = "0 * 0/1 * * *")
    public void printSubscribers() throws JsonProcessingException {
        logger.info("Subscribers=>[" + new ObjectMapper().writeValueAsString(subscribers) + "]");
    }
}
