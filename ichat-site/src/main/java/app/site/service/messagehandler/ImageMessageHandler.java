package app.site.service.messagehandler;

import app.site.model.receive.normal.ImageMessage;
import app.site.model.reply.Image;
import app.site.model.reply.ReplyingMessage;
import app.site.model.reply.ReplyingMessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author simple
 */
@Component
public class ImageMessageHandler extends AbstractMessageHandler<ImageMessage> {
    @Override
    protected ReplyingMessage innerHandle(ImageMessage message) {
        Image image = new Image();
        image.mediaId = message.mediaId;
        return new ReplyingMessageBuilder(message).image(image);
    }

    @Override
    protected void checkMessage(ImageMessage message) {

    }
}
