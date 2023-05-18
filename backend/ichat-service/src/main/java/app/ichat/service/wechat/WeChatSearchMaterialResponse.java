package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @author Steve Zou
 */
public class WeChatSearchMaterialResponse {
    @JsonProperty("item")
    public List<WeChatMaterial> materials;
    @JsonProperty("total_count")
    public Integer totalCount;
    @JsonProperty("item_count")
    public Integer itemCount;
}
