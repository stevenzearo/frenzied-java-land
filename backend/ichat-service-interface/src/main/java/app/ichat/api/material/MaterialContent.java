package app.ichat.api.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Steve Zou
 */
public class MaterialContent {
    @JsonProperty("news_item")
    public List<Article> articles;
    @JsonProperty("create_time")
    public ZonedDateTime createdTime;
    @JsonProperty("update_time")
    public ZonedDateTime updatedTime;
}
