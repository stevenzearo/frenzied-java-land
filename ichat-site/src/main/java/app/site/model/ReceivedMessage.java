package app.site.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steve Zou
 */
@XmlRootElement(name = "xml")
public class ReceivedMessage {
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
    @XmlElement(name = "MediaId")
    public String mediaId;
    @XmlElement(name = "PicUrl")
    public String picUrl;

    @XmlElement(name = "ArticleCount")
    public Integer articleCount;
    @XmlElement(name = "Location_X")
    public String locationX;
    @XmlElement(name = "Location_Y")
    public String locationY;
    @XmlElement(name = "Scale")
    public String scale;
    @XmlElement(name = "Label")
    public String label;
}
