package app.site.service.messagehandler;

import app.site.model.Image;
import app.site.model.ReceivedMessage;
import app.site.model.ReplyingMessage;
import org.springframework.stereotype.Service;

/**
 * @author simple
 */
@Service
public class ImageMessageHandler implements MessageHandler {
    @Override
    public ReplyingMessage handle(ReceivedMessage message) {
        ReplyingMessage replyingMessage = basic(message);
        replyingMessage.media = new Image() {{
            mediaId = message.mediaId;
        }};
        return replyingMessage;
    }
}
