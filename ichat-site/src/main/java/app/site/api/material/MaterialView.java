package app.site.api.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

/**
 * @author Steve Zou
 */
public class MaterialView {
    @JsonProperty("media_id")
    public String id;
    public MaterialContentView content;
    @JsonProperty("update_time")
    public ZonedDateTime updatedTime;
}
