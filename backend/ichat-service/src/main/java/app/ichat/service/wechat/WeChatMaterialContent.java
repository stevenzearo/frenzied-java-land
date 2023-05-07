package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Steve Zou
 */
public class WeChatMaterialContent {
    @JsonAlias("news_item")
    public List<WeChatArticle> articles;
    @JsonAlias("create_time")
    public ZonedDateTime createdTime;
    @JsonAlias("update_time")
    public ZonedDateTime updatedTime;
}
