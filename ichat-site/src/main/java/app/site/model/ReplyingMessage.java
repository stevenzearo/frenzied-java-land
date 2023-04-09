package app.site.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
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
    @XmlElementRefs({
        @XmlElementRef(type = Image.class),
        @XmlElementRef(type = Voice.class),
        @XmlElementRef(type = Video.class),
        @XmlElementRef(type = Article.class),
        @XmlElementRef(type = Location.class),
    })
    public Media media;
}
