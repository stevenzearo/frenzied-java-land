package app.ichat.api;

import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Steve Zou
 */
@FeignClient(value = "ichat-service", qualifiers = "auth-web-service")
public interface AuthWebService {
    @GetMapping(value = "/auth/access-token")
    Response<String> getAccessToken();
}
