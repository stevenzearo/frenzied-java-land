package app.site.model.reply;

import app.site.model.common.MessageType;
import app.site.model.receive.ReceivedMessage;
import java.util.UUID;

/**
 * @author Steve Zou
 */
public class ReplyingMessageBuilder {
    private final ReplyingMessage replyingMessage;

    public ReplyingMessageBuilder(ReceivedMessage receivedMessage) {
        ReplyingMessage replyingMessage = new ReplyingMessage();
        replyingMessage.fromUserName = receivedMessage.toUserName;
        replyingMessage.toUserName = receivedMessage.fromUserName;
        replyingMessage.createTime = System.currentTimeMillis();
        replyingMessage.msgId = UUID.randomUUID().toString();
        this.replyingMessage = replyingMessage;
    }

    public ReplyingMessage text(String content) {
        replyingMessage.msgType = MessageType.TEXT;
        replyingMessage.content = content;
        return replyingMessage;
    }

    public ReplyingMessage image(Image image) {
        replyingMessage.msgType = MessageType.IMAGE;
        replyingMessage.media = image;
        return replyingMessage;
    }

    public ReplyingMessage voice(Voice voice) {
        replyingMessage.msgType = MessageType.VOICE;
        replyingMessage.media = voice;
        return replyingMessage;
    }

    public ReplyingMessage video(Video video) {
        replyingMessage.msgType = MessageType.VOICE;
        replyingMessage.media = video;
        return replyingMessage;
    }

    public ReplyingMessage music(Music music) {
        replyingMessage.msgType = MessageType.MUSIC;
        replyingMessage.media = music;
        return replyingMessage;
    }

    public ReplyingMessage articles(Article article) {
        replyingMessage.msgType = MessageType.NEWS;
        replyingMessage.media = article;
        replyingMessage.articleCount = article.items.size();
        return replyingMessage;
    }
}
