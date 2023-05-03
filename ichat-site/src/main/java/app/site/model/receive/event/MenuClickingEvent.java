package app.site.model.receive.event;

import app.site.model.receive.EventMessage;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public class MenuClickingEvent extends EventMessage {
    @XmlElement(name = "EventKey")
    public String eventKey;
}
