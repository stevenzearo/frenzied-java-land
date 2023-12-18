package app.ichat.api;

import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Steve Zou
 */
@FeignClient(value = "ichat-service", qualifiers = "wechat-oauth-web-service")
public interface WeChatOAuthWebService {
    @PostMapping(value = "/wechat/oauth/website/access-token")
    Response<String> getWebsiteAccessToken(@RequestParam("code") String code);
}
