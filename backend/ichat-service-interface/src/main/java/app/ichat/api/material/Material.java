package app.ichat.api.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

/**
 * @author Steve Zou
 */
public class Material {
    @JsonProperty("media_id")
    public String id;
    public MaterialContent content;
    @JsonProperty("update_time")
    public ZonedDateTime updatedTime;
}
