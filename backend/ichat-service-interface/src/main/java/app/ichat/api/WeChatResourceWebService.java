package app.ichat.api;

import app.ichat.api.material.SearchMaterialRequest;
import app.ichat.api.material.SearchMaterialResponse;
import app.ichat.api.material.UploadMaterialRequest;
import app.ichat.api.material.UploadMaterialResponse;
import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Steve Zou
 */
@FeignClient(value = "ichat-service", qualifiers = "wechat-resource-web-service", url = Context.ICHAT_SERVICE_URL)
public interface WeChatResourceWebService {
    @GetMapping(value = "/wechat/image")
    Response<byte[]> getImage(@RequestParam("url") String url);

    @PostMapping(value = "/wechat/material")
    Response<SearchMaterialResponse> searchMaterial(@RequestParam("access_token") String accessToken, @RequestBody SearchMaterialRequest request);

    @PostMapping(value = "/wechat/material/upload")
    UploadMaterialResponse uploadMaterial(@RequestParam("access_token") String accessToken, @RequestBody UploadMaterialRequest request);
}
