package app.site.model.receive;

import app.site.model.common.MessageType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steve Zou
 */
@XmlRootElement(name = "xml")
public abstract class ReceivedMessage {
    @XmlElement(name = "ToUserName")
    public String toUserName;
    @XmlElement(name = "FromUserName")
    public String fromUserName;
    @XmlElement(name = "MsgType")
    public MessageType msgType;
    @XmlElement(name = "CreateTime")
    public Long createTime;
}
