package app.site.api;

import app.site.service.AuthService;
import app.site.service.ChatService;
import javax.annotation.Resource;
import javax.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
@RequestMapping("hland")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Resource
    AuthService authService;
    @Resource
    ChatService chatService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    String auth(@QueryParam("signature") String signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce, @QueryParam("echostr") String echostr) {
        return authService.auth(signature, timestamp, nonce, echostr);
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    byte[] chat(@RequestBody String body) {
        logger.info("receive message:" + body);
        byte[] bytes = chatService.chat(body);
        logger.info("reply:" + new String(bytes));
        return bytes;
    }
}
