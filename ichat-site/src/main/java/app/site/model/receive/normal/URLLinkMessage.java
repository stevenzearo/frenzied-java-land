package app.site.model.receive.normal;

import app.site.model.receive.NormalMessage;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Steve Zou
 */
public class URLLinkMessage extends NormalMessage {
    @XmlElement(name = "Title")
    public String title;
    @XmlElement(name = "Description")
    public String description;
    @XmlElement(name = "url")
    public String url;
}
