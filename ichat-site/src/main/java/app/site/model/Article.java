package app.site.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steve Zou
 */
@XmlRootElement(name = "Articles")
public class Article extends Media {
    @XmlElementRef
   public List<ArticleItem> items;
}
