package app.site.model.reply;

import app.site.model.common.MessageType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steve Zou
 */
@XmlRootElement(name = "xml")
public class ReplyingMessage {
    @XmlElement(name = "ToUserName")
    public String toUserName;
    @XmlElement(name = "FromUserName")
    public String fromUserName;
    @XmlElement(name = "MsgType")
    public MessageType msgType;
    @XmlElement(name = "CreateTime")
    public Long createTime;
    @XmlElement(name = "MsgId")
    public String msgId;
    @XmlElement(name = "Content")
    public String content;
    @XmlElement(name = "ArticleCount")
    public Integer articleCount;

    @XmlElements({
        @XmlElement(type = Image.class),
        @XmlElement(type = Voice.class),
        @XmlElement(type = Music.class),
        @XmlElement(type = Video.class),
        @XmlElement(type = Article.class),
    })
    public Media media;
}
