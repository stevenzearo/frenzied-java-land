package api.client;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author Steve Zou
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        HttpUriRequest request = RequestBuilder.get()
            .setUri("https://api.weixin.qq.com/cgi-bin/token")
            .addParameter("grant_type", "client_credential")
            .addParameter("appid", "***")
            .addParameter("secret", "***")
            .build();
        CloseableHttpResponse response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
         * {"access_token":"****","expires_in":7200}
         *
         * */
    }
}
