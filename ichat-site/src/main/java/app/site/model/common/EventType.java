package app.site.model.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author simple
 */
@XmlEnum
public enum EventType {
    @XmlEnumValue("subscribe")
    SUBSCRIBE,
    @XmlEnumValue("unsubscribe")
    UNSUBSCRIBE
}
