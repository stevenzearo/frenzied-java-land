package app.site.api.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @author Steve Zou
 */
public class SearchMaterialAJAXResponse {
    @JsonProperty("item")
    public List<MaterialView> materials;
    @JsonProperty("total_count")
    public Integer totalCount;
    @JsonProperty("item_count")
    public Integer itemCount;
}
