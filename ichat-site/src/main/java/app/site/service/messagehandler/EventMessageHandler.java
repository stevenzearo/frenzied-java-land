package app.site.service.messagehandler;

import app.site.model.common.EventType;
import app.site.model.receive.EventMessage;
import app.site.model.reply.ReplyingMessage;
import app.site.model.reply.ReplyingMessageBuilder;
import app.web.error.WebException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author simple
 */
@Component
public abstract class EventMessageHandler<T extends EventMessage> extends AbstractMessageHandler<T> {
    private final Set<String> cache = new HashSet<>();
    private final Set<String> subscribers = new HashSet<>();
    private final Logger logger = LoggerFactory.getLogger(EventMessageHandler.class);

    @Override
    protected ReplyingMessage innerHandle(T message) {
        checkRequested(message);
        if (message.event == EventType.SUBSCRIBE) {
            subscribers.add(message.fromUserName);
            return welcomeMessage(message);
        }
        subscribers.remove(message.fromUserName);
        return null;
    }

    @Override
    protected void checkMessage(T message) {

    }

    private ReplyingMessage welcomeMessage(T message) {
        String welcomeStr = "你好，欢迎关注野生程序员聚集地";
        return new ReplyingMessageBuilder(message).text(welcomeStr);
    }

    private void checkRequested(T message) {
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
