package app.site.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
public class WeChatOAuthController {
    @PostMapping(value = "/api/ajax/wechat/website/access-token")
    public String generateAccessToken(@RequestParam String code) {
        return null;
    }
}
