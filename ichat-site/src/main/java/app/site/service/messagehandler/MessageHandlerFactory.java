package app.site.service.messagehandler;

import app.site.model.common.EventType;
import app.site.model.common.MessageType;
import app.site.model.common.XMLUtil;
import app.site.model.receive.EventMessage;
import app.site.model.receive.ReceivedMessage;
import app.site.model.receive.event.SubscriptionEvent;
import app.site.model.receive.normal.DefaultReceivedMessage;
import app.site.model.receive.normal.ImageMessage;
import app.site.model.receive.normal.TextMessage;
import app.site.model.reply.Image;
import app.site.model.reply.ReplyingMessage;
import app.site.model.reply.ReplyingMessageBuilder;
import app.web.error.ConflictException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author simple
 */
@Component
public final class MessageHandlerFactory {
    @Autowired
    TextMessageHandler textMessageHandler;
    @Autowired
    ImageMessageHandler imageMessageHandler;
    @Autowired
    SubscribetionEventMessageHandler eventMessageHandler;
    private final static Map<MessageType, AbstractMessageHandler<?>> messageHandlereMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public ReplyingMessage handle(String xmlContent) {
        ReceivedMessage receivedMessage = XMLUtil.toEntity(xmlContent, DefaultReceivedMessage.class);
        if (MessageType.TEXT == receivedMessage.msgType) {
            TextMessage textMessage = toConcreteMessage(xmlContent, TextMessage.class);
            AbstractMessageHandler<TextMessage> handler = (AbstractMessageHandler<TextMessage>) getMessageHandler(MessageType.TEXT);
            return handler.handle(textMessage);
        } else if (MessageType.IMAGE == receivedMessage.msgType) {
            ImageMessage imageMessage = toConcreteMessage(xmlContent, ImageMessage.class);
            Image image = new Image();
            image.mediaId = imageMessage.mediaId;
            return new ReplyingMessageBuilder(receivedMessage).image(image);
        } else if (MessageType.EVENT == receivedMessage.msgType) {
            EventMessage eventMessage = toConcreteMessage(xmlContent, EventMessage.class);
            EventType event = eventMessage.event;
            if (EventType.SUBSCRIBE == event || EventType.UNSUBSCRIBE == event) {
                SubscriptionEvent subscriptionEvent = toConcreteMessage(xmlContent, SubscriptionEvent.class);
                AbstractMessageHandler<SubscriptionEvent> handler = (AbstractMessageHandler<SubscriptionEvent>) getMessageHandler(MessageType.EVENT);
                return handler.handle(subscriptionEvent);
            } else if (EventType.VIEW == event) {
                return new ReplyingMessageBuilder(receivedMessage).text(ReplyingContentUtil.randomChoice(ReplyingContentUtil.getUnsupportedMsgReplies()));
            }
        }
        throw new ConflictException("Unsupported message");
    }

    private AbstractMessageHandler<?> getMessageHandler(MessageType msgType) {
        AbstractMessageHandler<?> handler = messageHandlereMap.get(msgType);
        if (handler == null) {
            // Same object mapped to same key, no need consider concurrent issue
            if (MessageType.TEXT == msgType) {
                handler = textMessageHandler;
                messageHandlereMap.putIfAbsent(MessageType.TEXT, textMessageHandler);
            } else if (MessageType.IMAGE == msgType) {
                handler = imageMessageHandler;
                messageHandlereMap.putIfAbsent(MessageType.IMAGE, imageMessageHandler);
            } else if (MessageType.EVENT == msgType) {
                handler = eventMessageHandler;
                messageHandlereMap.putIfAbsent(MessageType.EVENT, eventMessageHandler);
            }
        }
        return handler;
    }

    private <T extends ReceivedMessage> T toConcreteMessage(String xmlContent, Class<T> tClass) {
        return XMLUtil.toEntity(xmlContent, tClass);
    }
}
