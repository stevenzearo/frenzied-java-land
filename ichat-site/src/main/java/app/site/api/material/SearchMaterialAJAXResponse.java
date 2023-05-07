package app.site.api.material;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

/**
 * @author Steve Zou
 */
public class SearchMaterialAJAXResponse {
    @JsonAlias("item")
    public List<MaterialView> materials;
    @JsonAlias("total_count")
    public Integer totalCount;
    @JsonAlias("item_count")
    public Integer itemCount;
}
