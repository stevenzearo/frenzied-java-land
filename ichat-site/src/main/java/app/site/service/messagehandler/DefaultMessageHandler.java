package app.site.service.messagehandler;

import app.site.model.ReceivedMessage;
import app.site.model.ReplyingMessage;
import app.web.error.WebException;
import org.springframework.stereotype.Service;

/**
 * @author simple
 */
@Service
public class DefaultMessageHandler implements MessageHandler {
    @Override
    public ReplyingMessage handle(ReceivedMessage receivedMessage) {
        throw new WebException("Unsupported media type");
    }
}
