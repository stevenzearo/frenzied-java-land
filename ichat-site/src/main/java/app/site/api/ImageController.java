package app.site.api;

import app.site.api.image.GetImageRequest;
import app.site.api.image.GetImageResponse;
import app.web.error.TuringIntegrationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    private final Logger logger = LoggerFactory.getLogger(ImageController.class);
    @ResponseBody
    @CrossOrigin("*")
    @PutMapping("/article-image")
    public GetImageResponse getImage(@RequestBody GetImageRequest request) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper mapper = new ObjectMapper();
            String reqEntity = mapper.writeValueAsString(request);
            logger.info("chat request entity: {}", reqEntity);

            HttpUriRequest httpRequest = RequestBuilder.get()
                .setUri(request.url)
                .build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);

            HttpEntity entity = httpResponse.getEntity();
            GetImageResponse response = new GetImageResponse();
            response.data = entity.getContent().readAllBytes();
            return response;
        } catch (IOException e) {
            throw new TuringIntegrationException("Failed to chat with Turing platform.", e);
        }
    }
}
