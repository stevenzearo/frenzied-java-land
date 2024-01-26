package app.ichat.api;

import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Steve Zou
 */
@FeignClient(value = "ichat-service", qualifiers = "auth-web-service", url = Context.ICHAT_SERVICE_URL)
public interface AuthWebService {
    @GetMapping(value = "/auth/access-token")
    Response<String> getAccessToken(@RequestParam("renew") Boolean renew);
}
