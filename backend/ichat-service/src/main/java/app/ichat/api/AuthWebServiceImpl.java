package app.ichat.api;

import app.ichat.service.AuthService;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author Steve Zou
 */
@Component
public class AuthWebServiceImpl implements AuthWebService {
    @Resource
    AuthService authService;

    @Override
    public Response<String> getAccessToken() {
        return ResponseHelper.okOf(authService.getAccessToken());
    }
}
