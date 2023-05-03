package app.site.model.receive.normal;

import app.site.model.receive.NormalMessage;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public class LocationMessage extends NormalMessage {
    @XmlElement(name = "Location_X")
    public String locationX;
    @XmlElement(name = "Location_Y")
    public String locationY;
    @XmlElement(name = "Scale")
    public String scale;
    @XmlElement(name = "Label")
    public String label;
}
