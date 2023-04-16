package app.site.service.messagehandler;

import app.site.model.ReceivedMessage;
import app.site.model.ReplyingMessage;

import java.util.UUID;

/**
 * @author simple
 */
public interface MessageHandler {
    ReplyingMessage handle(ReceivedMessage message);

    default ReplyingMessage basic(ReceivedMessage message) {
        ReplyingMessage replyingMessage = new ReplyingMessage();
        replyingMessage.fromUserName = message.toUserName;
        replyingMessage.toUserName = message.fromUserName;
        replyingMessage.createTime = System.currentTimeMillis();
        replyingMessage.msgId = UUID.randomUUID().toString();
        return replyingMessage;
    }
}
