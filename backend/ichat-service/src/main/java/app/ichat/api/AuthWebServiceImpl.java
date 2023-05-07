package app.ichat.api;

import app.ichat.service.AuthService;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
public class AuthWebServiceImpl implements AuthWebService {
    @Resource
    AuthService authService;

    @Override
    public Response<String> getAccessToken(Boolean renew) {
        return ResponseHelper.okOf(authService.getAccessToken(renew));
    }
}
