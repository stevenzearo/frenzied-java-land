package app.site.model.receive;

import app.site.model.common.EventType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public class EventMessage extends ReceivedMessage {
    @XmlElement(name = "Event")
    public EventType event;
}
