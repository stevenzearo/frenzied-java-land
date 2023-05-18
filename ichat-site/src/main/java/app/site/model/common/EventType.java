package app.site.model.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @author simple
 */
@XmlEnum
public enum EventType {
    @XmlEnumValue("subscribe")
    SUBSCRIBE,
    @XmlEnumValue("unsubscribe")
    UNSUBSCRIBE,
    @XmlEnumValue("SCAN")
    SCAN,
    @XmlEnumValue("VIEW")
    VIEW,
    @XmlEnumValue("LOCATION")
    LOCATION
}
