package app.site.model.receive.normal;

import app.site.model.receive.NormalMessage;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public class ImageMessage extends NormalMessage {
    @XmlElement(name = "MediaId")
    public String mediaId;
    @XmlElement(name = "PicUrl")
    public String picUrl;
}
