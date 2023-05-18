package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Steve Zou
 */
public class WeChatMaterialContent {
    @JsonProperty("news_item")
    public List<WeChatArticle> articles;
    @JsonProperty("create_time")
    public ZonedDateTime createdTime;
    @JsonProperty("update_time")
    public ZonedDateTime updatedTime;
}
