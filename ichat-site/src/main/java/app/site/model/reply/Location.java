package app.site.model.reply;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steve Zou
 */
@Deprecated
@XmlRootElement(name = "Location")
public class Location extends Media {
    @XmlElement(name = "Location_X")
    public String locationX;
    @XmlElement(name = "Location_Y")
    public String locationY;
    @XmlElement(name = "Scale")
    public String scale;
    @XmlElement(name = "Label")
    public String label;
}
