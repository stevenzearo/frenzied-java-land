package app.site.service.messagehandler;

import app.site.model.receive.ReceivedMessage;
import app.site.model.reply.Image;
import app.site.model.reply.ReplyingMessage;
import app.site.model.reply.ReplyingMessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author simple
 */
@Component
public class ImageMessageHandler extends AbstractMessageHandler {
    @Override
    protected ReplyingMessage innerHandle(ReceivedMessage message) {
        Image image = new Image();
        image.mediaId = message.mediaId;
        return new ReplyingMessageBuilder(message).image(image);
    }

    @Override
    protected void checkMessage(ReceivedMessage message) {

    }
}
