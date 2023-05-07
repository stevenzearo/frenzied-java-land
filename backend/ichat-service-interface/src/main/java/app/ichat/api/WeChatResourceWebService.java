package app.ichat.api;

import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
@FeignClient(value = "ichat-service", qualifiers = "wechat-resource-web-service")
public interface WeChatResourceWebService {
    @RequestMapping(value = "/wechat/image", method = RequestMethod.GET)
    Response<byte[]> getImage(@RequestParam("url") String url);
}
