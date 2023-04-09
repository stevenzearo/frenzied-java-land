package app.site.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steve Zou
 */
@XmlRootElement(name = "xml")
public class TextMessage {
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
}
