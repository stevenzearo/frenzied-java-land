package app.ichat.api.material;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.ZonedDateTime;

/**
 * @author Steve Zou
 */
public class Material {
    @JsonAlias("media_id")
    public String id;
    public MaterialContent content;
    @JsonAlias("update_time")
    public ZonedDateTime updatedTime;
}
