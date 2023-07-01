package app.ichat.api;

import app.ichat.api.material.SearchMaterialRequest;
import app.ichat.api.material.SearchMaterialResponse;
import app.ichat.api.material.UploadMaterialRequest;
import app.ichat.api.material.UploadMaterialResponse;
import app.ichat.service.WeChatImageService;
import app.ichat.service.WeChatMaterialService;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
public class WeChatResourceWebServiceImpl implements WeChatResourceWebService {
    private final Logger logger = LoggerFactory.getLogger(WeChatResourceWebServiceImpl.class);
    @Autowired
    WeChatImageService imageService;
    @Autowired
    WeChatMaterialService materialService;

    @Override
    public Response<byte[]> getImage(String url) {
        logger.info("get image url: {}", url);
        return ResponseHelper.okOf(imageService.getImage(url));
    }

    @Override
    public Response<SearchMaterialResponse> searchMaterial(String accessToken, SearchMaterialRequest request) {
        return ResponseHelper.encloseWithException(() -> materialService.searchMaterial(accessToken, request));
    }

    @Override
    public UploadMaterialResponse uploadMaterial(String accessToken, UploadMaterialRequest request) {
        return materialService.uploadMaterial(accessToken, request);
    }
}
