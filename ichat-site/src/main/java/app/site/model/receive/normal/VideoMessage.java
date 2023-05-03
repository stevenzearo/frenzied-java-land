package app.site.model.receive.normal;

import app.site.model.receive.NormalMessage;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public class VideoMessage extends NormalMessage {
    @XmlElement(name = "MediaId")
    public String mediaId;
    @XmlElement(name = "ThumbMediaId")
    public String thumbMediaId;
}
