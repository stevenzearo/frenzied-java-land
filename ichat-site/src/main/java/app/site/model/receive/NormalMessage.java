package app.site.model.receive;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public abstract class NormalMessage extends ReceivedMessage {
    @XmlElement(name = "MsgId")
    public String msgId;
    @XmlElement(name = "MsgDataId")
    public String msgDataId;
    @XmlElement(name = "Idx")
    public String idIndex; // start to 1
}
