package app.ichat.api;

import app.ichat.service.WeChatImageService;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Steve Zou
 */
@Component
public class WeChatResourceWebServiceImpl implements WeChatResourceWebService {
    @Autowired
    WeChatImageService imageService;

    @Override
    public Response<byte[]> getImage(String url) {
        return ResponseHelper.okOf(imageService.getImage(url));
    }
}
