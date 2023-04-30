package app.site.service.messagehandler;

import app.site.model.receive.ReceivedMessage;
import app.site.model.reply.ReplyingMessage;

/**
 * @author Steve Zou
 */
public abstract class AbstractMessageHandler {
    final public ReplyingMessage handle(ReceivedMessage message) {
        checkMessage(message);
        return innerHandle(message);
    }

    protected abstract ReplyingMessage innerHandle(ReceivedMessage message);

    protected abstract void checkMessage(ReceivedMessage message);
}
