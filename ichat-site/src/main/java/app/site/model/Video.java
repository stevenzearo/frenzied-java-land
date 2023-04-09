package app.site.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steve Zou
 */
@XmlRootElement(name = "Video")
public class Video extends Media {
    @XmlElement(name = "MediaId")
    public String mediaId;
    @XmlElement(name = "Title")
    public String title;
    @XmlElement(name = "Description")
    public String description;
}
