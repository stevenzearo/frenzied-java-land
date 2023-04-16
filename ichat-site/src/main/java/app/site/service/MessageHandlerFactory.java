package app.site.service;

import app.site.model.MessageType;
import app.site.service.messagehandler.DefaultMessageHandler;
import app.site.service.messagehandler.EventMessageHandler;
import app.site.service.messagehandler.ImageMessageHandler;
import app.site.service.messagehandler.MessageHandler;
import app.site.service.messagehandler.TextMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author simple
 */
@Service
public class MessageHandlerFactory {
    @Autowired
    DefaultMessageHandler defaultMessageHandler;
    @Autowired
    EventMessageHandler eventMessageHandler;
    @Autowired
    ImageMessageHandler imageMessageHandler;
    @Autowired
    TextMessageHandler textMessageHandler;

    public MessageHandler get(MessageType type) {
        // for singleton
        switch (type) {
            case TEXT:
                return textMessageHandler;
            case IMAGE:
                return imageMessageHandler;
            case EVENT:
                return eventMessageHandler;
            default:
                return defaultMessageHandler;
        }
    }
}
