package app.site.model.reply;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steve Zou
 */
@XmlRootElement(name = "item")
public class ArticleItem {
    @XmlElement(name = "Title")
    public String title;
    @XmlElement(name = "Description")
    public String description;
    @XmlElement(name = "PicUrl")
    public String picUrl;
    @XmlElement(name = "Url")
    public String url;
}
