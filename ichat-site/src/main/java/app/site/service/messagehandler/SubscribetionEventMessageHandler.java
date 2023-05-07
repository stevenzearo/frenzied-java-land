package app.site.service.messagehandler;

import app.site.model.common.EventType;
import app.site.model.receive.event.SubscriptionEvent;
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
public final class SubscribetionEventMessageHandler extends EventMessageHandler<SubscriptionEvent> {
    private final Set<String> cache = new HashSet<>();
    private final Set<String> subscribers = new HashSet<>();
    private final Logger logger = LoggerFactory.getLogger(SubscribetionEventMessageHandler.class);

    @Override
    protected ReplyingMessage innerHandle(SubscriptionEvent message) {
        checkRequested(message);
        if (EventType.SUBSCRIBE == message.event) {
            subscribers.add(message.fromUserName);
            return welcomeMessage(message);
        } else if (EventType.UNSUBSCRIBE == message.event) {
            logger.warn("User unsubscribed!");
        }
        subscribers.remove(message.fromUserName);
        return new ReplyingMessageBuilder(message).text("");
    }

    @Override
    protected void checkMessage(SubscriptionEvent message) {

    }

    private ReplyingMessage welcomeMessage(SubscriptionEvent message) {
        return new ReplyingMessageBuilder(message).text(ReplyingContentUtil.welcomeMessage());
    }

    private void checkRequested(SubscriptionEvent message) {
        String requestId = String.format("%s-%s", message.fromUserName, message.createTime);
        if (cache.contains(requestId)) {
            throw new WebException("duplicated request");
        }
        cache.add(requestId);
    }

    @Scheduled(cron = "0 * 0/1 * * *")
    public void printSubscribers() throws JsonProcessingException {
        logger.info("Subscribers=>[" + mapper.writeValueAsString(subscribers) + "]");
    }
}
