package app.site.model.receive.normal;

import app.site.model.receive.NormalMessage;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public class VoiceMessage extends NormalMessage {
    @XmlElement(name = "MediaId")
    public String mediaId;
    @XmlElement(name = "Format")
    public String format;
    @XmlElement(name = "Recognition")
    public String recognition;
}
