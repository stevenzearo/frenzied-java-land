package app.site.model.common;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @author Steve Zou
 */
@XmlEnum
public enum MessageType implements Serializable {
    @XmlEnumValue("text")
    TEXT("text"),
    @XmlEnumValue("image")
    IMAGE("image"),
    @XmlEnumValue("event")
    EVENT("event"),
    @XmlEnumValue("voice")
    VOICE("voice"),
    @XmlEnumValue("video")
    VIDEO("video"),
    @XmlEnumValue("shortvideo")
    SHORT_VIDEO("shortvideo"),
    @XmlEnumValue("music")
    MUSIC("music"),
    @XmlEnumValue("news")
    NEWS("news"),
    @XmlEnumValue("location")
    LOCATION("location");

    public final String value;

    MessageType(String value) {
        this.value = value;
    }
}
