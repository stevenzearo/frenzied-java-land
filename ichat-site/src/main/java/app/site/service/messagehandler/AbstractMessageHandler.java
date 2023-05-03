package app.site.service.messagehandler;

import app.site.model.receive.ReceivedMessage;
import app.site.model.reply.ReplyingMessage;

/**
 * @author Steve Zou
 */
public abstract class AbstractMessageHandler<T extends ReceivedMessage> {
    final public ReplyingMessage handle(T message) {
        checkMessage(message);
        return innerHandle(message);
    }

    protected abstract ReplyingMessage innerHandle(T message);

    protected abstract void checkMessage(T message);
}
