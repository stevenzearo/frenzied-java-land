package app.site.api.material;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.ZonedDateTime;

/**
 * @author Steve Zou
 */
public class MaterialView {
    @JsonAlias("media_id")
    public String id;
    public MaterialContentView content;
    @JsonAlias("update_time")
    public ZonedDateTime updatedTime;
}
