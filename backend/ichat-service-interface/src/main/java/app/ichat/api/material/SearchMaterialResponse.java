package app.ichat.api.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @author Steve Zou
 */
public class SearchMaterialResponse {
    @JsonProperty("item")
    public List<Material> materials;
    @JsonProperty("total_count")
    public Integer totalCount;
    @JsonProperty("item_count")
    public Integer itemCount;
}
