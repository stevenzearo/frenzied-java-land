package app.site.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steve Zou
 */
@XmlRootElement(name = "Music")
public class Music extends Media {
    @XmlElement(name = "ThumbMediaId")
    public String thumbMediaId;
    @XmlElement(name = "MusicUrl")
    public String musicUrl;
    @XmlElement(name = "HQMusicUrl")
    public String hqMusicUrl;
    @XmlElement(name = "Title")
    public String title;
    @XmlElement(name = "Description")
    public String description;
}
