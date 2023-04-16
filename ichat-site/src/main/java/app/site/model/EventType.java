package app.site.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author simple
 */
@XmlType()
@XmlEnum(String.class)
public enum EventType {
    @XmlEnumValue("subscribe")
    SUBSCRIBE,
    @XmlEnumValue("unsubscribe")
    UNSUBSCRIBE
}
