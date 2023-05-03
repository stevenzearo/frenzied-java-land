package app.site.model.receive.event;

import app.site.model.receive.EventMessage;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public class ScanningEvent extends EventMessage {
    @XmlElement(name = "EventKey")
    public String eventKey;
    @XmlElement(name = "Ticket")
    public String ticket;
}
