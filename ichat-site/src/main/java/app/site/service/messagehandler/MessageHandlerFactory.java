package app.site.service.messagehandler;

import app.site.model.common.MessageType;
import app.site.model.common.XMLUtil;
import app.site.model.receive.ReceivedMessage;
import app.site.model.reply.ReplyingMessage;
import app.web.error.ConflictException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author simple
 */
@Component
public class MessageHandlerFactory {
    @Autowired
    TextMessageHandler textMessageHandler;
    @Autowired
    ImageMessageHandler imageMessageHandler;
    @Autowired
    EventMessageHandler eventMessageHandler;
    private final static Map<MessageType, Object> messageHandlereMap = new HashMap<>();

    public ReplyingMessage handle(String xmlContent) {
        ReceivedMessage receivedMessage = XMLUtil.toEntity(xmlContent, ReceivedMessage.class);
        AbstractMessageHandler handler= getMessageHandler(receivedMessage.msgType);;
        if (handler == null) {
            throw new ConflictException(String.format("Unsupported message type=%s", receivedMessage.msgType));
        }
        return handler.handle(receivedMessage);
    }

    private AbstractMessageHandler getMessageHandler(MessageType msgType) {
        AbstractMessageHandler handler = (AbstractMessageHandler) messageHandlereMap.get(msgType);
        if (handler == null) {
            // Same object mapped to same key, no need consider concurrent issue
            if (MessageType.TEXT == msgType) {
                handler = textMessageHandler;
                messageHandlereMap.putIfAbsent(MessageType.TEXT, textMessageHandler);
            } else if (MessageType.IMAGE == msgType) {
                handler = imageMessageHandler;
                messageHandlereMap.putIfAbsent(MessageType.IMAGE, imageMessageHandler);
            }else if (MessageType.EVENT == msgType) {
                handler = eventMessageHandler;
                messageHandlereMap.putIfAbsent(MessageType.EVENT, eventMessageHandler);
            }
        }
        return handler;
    }
}
