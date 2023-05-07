package app.site.api.material;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Steve Zou
 */
public class MaterialContentView {
    @JsonAlias("news_item")
    public List<ArticleView> articles;
    @JsonAlias("create_time")
    public ZonedDateTime createdTime;
    @JsonAlias("update_time")
    public ZonedDateTime updatedTime;
}
