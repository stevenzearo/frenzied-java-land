package app.site.model.receive.event;

import app.site.model.receive.EventMessage;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public class ReportingLocationEvent extends EventMessage {
    @XmlElement(name = "Latitude")
    public String latitude;
    @XmlElement(name = "Longitude")
    public String longitude;
    @XmlElement(name = "Precision")
    public String precision;
}
