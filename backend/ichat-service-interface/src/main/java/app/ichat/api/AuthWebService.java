package app.ichat.api;

import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
@RequestMapping("/auth")
@FeignClient(value = "auth-web-service")
public interface AuthWebService {
    @RequestMapping(value = "/access-token", method = RequestMethod.GET)
    Response<String> getAccessToken();

}
