package app.ichat.service;

import app.web.error.WeChatIntegrationException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class WeChatImageService {
    private final Logger logger = LoggerFactory.getLogger(WeChatImageService.class);
    @Autowired(required = false)
    ObjectMapper mapper;

    public byte[] getImage(String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpUriRequest httpRequest = RequestBuilder.get()
                .setUri(url)
                .build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);

            HttpEntity entity = httpResponse.getEntity();
            return entity.getContent().readAllBytes();
        } catch (IOException e) {
            throw new WeChatIntegrationException(String.format("Failed to get image, url=%s", url), e);
        }
    }

}
