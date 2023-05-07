package app.ichat.api;

import app.ichat.api.material.SearchMaterialRequest;
import app.ichat.api.material.SearchMaterialResponse;
import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Steve Zou
 */
@FeignClient(value = "ichat-service", qualifiers = "wechat-resource-web-service")
public interface WeChatResourceWebService {
    @GetMapping(value = "/wechat/image")
    Response<byte[]> getImage(@RequestParam("url") String url);

    @PostMapping(value = "/wechat/material")
    Response<SearchMaterialResponse> searchMaterial(@RequestParam("access_token") String accessToken, @RequestBody SearchMaterialRequest request);
}
