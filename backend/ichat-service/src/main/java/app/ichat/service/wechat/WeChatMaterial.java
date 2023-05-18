package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

/**
 * @author Steve Zou
 */
public class WeChatMaterial {
    @JsonProperty("media_id")
    public String id;
    public WeChatMaterialContent content;
    @JsonProperty("update_time")
    public ZonedDateTime updatedTime;
}
