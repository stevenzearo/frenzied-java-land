package app.ichat.api;

import app.ichat.service.turing.TuringChatRequest;
import app.web.error.WeChatIntegrationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @author Steve Zou
 */
public class TuringChatTest {

    @Test
    public void test() {

        TuringChatRequest chatRequest = new TuringChatRequest();
        chatRequest.openId = "a5b127eb-b60e-4b40-bc0c-f52aed510b4c";
        chatRequest.text = "你好呀";


        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper mapper = new ObjectMapper();
            String reqEntity = mapper.writeValueAsString(chatRequest);
            System.out.println(reqEntity);
            StringEntity stringEntity = new StringEntity(reqEntity, ContentType.APPLICATION_JSON);
            HttpUriRequest request = RequestBuilder.post()
                .setUri("http://chatbot-api.turingapi.com/v1/wechat")
                .setEntity(stringEntity)
                .build();
            CloseableHttpResponse httpResponse = httpClient.execute(request);
            HttpEntity entity = httpResponse.getEntity();
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        } catch (IOException e) {
            throw new WeChatIntegrationException("Failed to get access_token from WeChat platform.", e);
        }
    }

}
/*
* {"openId":"a5b127eb-b60e-4b40-bc0c-f52aed510b4c","text":"你好呀"}
* */