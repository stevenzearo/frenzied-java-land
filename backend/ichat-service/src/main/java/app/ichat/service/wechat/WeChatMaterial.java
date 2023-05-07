package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.ZonedDateTime;

/**
 * @author Steve Zou
 */
public class WeChatMaterial {
    @JsonAlias("media_id")
    public String id;
    public WeChatMaterialContent content;
    @JsonAlias("update_time")
    public ZonedDateTime updatedTime;
}
