package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

/**
 * @author Steve Zou
 */
public class WeChatSearchMaterialResponse {
    @JsonAlias("item")
    public List<WeChatMaterial> materials;
    @JsonAlias("total_count")
    public Integer totalCount;
    @JsonAlias("item_count")
    public Integer itemCount;
}
