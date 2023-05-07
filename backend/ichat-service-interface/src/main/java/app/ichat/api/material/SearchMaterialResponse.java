package app.ichat.api.material;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

/**
 * @author Steve Zou
 */
public class SearchMaterialResponse {
    @JsonAlias("item")
    public List<Material> materials;
    @JsonAlias("total_count")
    public Integer totalCount;
    @JsonAlias("item_count")
    public Integer itemCount;
}
