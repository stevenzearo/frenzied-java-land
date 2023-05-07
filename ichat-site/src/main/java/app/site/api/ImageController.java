package app.site.api;

import app.ichat.api.WeChatResourceWebService;
import app.site.api.image.GetImageRequest;
import app.site.api.image.GetImageResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    private final Logger logger = LoggerFactory.getLogger(ImageController.class);
    @Autowired
    WeChatResourceWebService resourceWebService;

    @Deprecated
    @ResponseBody
    @CrossOrigin("*")
    @PutMapping("/article-image")
    public GetImageResponse getImage(@RequestBody GetImageRequest request) {
        return boGetGetImage(request.url);
    }

    @ResponseBody
    @CrossOrigin({"http://localhost", "https://localhost", "https://fjavaland*.cpolar.top"})
    @GetMapping("/article-image")
    public GetImageResponse getImage(@RequestParam("url") String url) {
        return boGetGetImage(url);
    }

    private GetImageResponse boGetGetImage(String url) {
        Response<byte[]> image = resourceWebService.getImage(url);
        byte[] data = ResponseHelper.fetchDataWithException(image);
        GetImageResponse response = new GetImageResponse();
        response.data = data;
        return response;
    }
}
