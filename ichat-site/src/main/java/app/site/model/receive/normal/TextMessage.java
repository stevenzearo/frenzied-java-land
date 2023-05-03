package app.site.model.receive.normal;

import app.site.model.receive.NormalMessage;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public class TextMessage extends NormalMessage {
    @XmlElement(name = "Content")
    public String content;
}
