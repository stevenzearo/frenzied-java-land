package app.site.model.reply;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steve Zou
 */
@XmlRootElement(name = "Image")
public class Image extends Media {
    @XmlElement(name = "MediaId")
    public String mediaId;
}
