package app.ichat.service;

import app.ichat.config.wechat.WeChatPublicAccountConfig;
import app.ichat.domain.AccessTokenCache;
import app.ichat.service.wechat.WeChatAccessTokenResponse;
import app.ichat.service.wechat.WeChatGetAccessTokenRequest;
import app.web.error.WeChatIntegrationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.ZonedDateTime;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private static final Object lock = new Object();
    private AccessTokenCache accessTokenCache;
    @Autowired
    private WeChatPublicAccountConfig accountConfig;
    @Autowired(required = false)
    ObjectMapper mapper;

    public String getAccessToken(Boolean renew) {
        if (Boolean.TRUE.equals(renew)) {
            synchronized (lock) {
                accessTokenCache = getAppAccessToken();
                return accessTokenCache.accessToken;
            }
        }
        if (isValidToken(accessTokenCache)) {
            return accessTokenCache.accessToken;
        } else {
            synchronized (lock) {
                if (!isValidToken(accessTokenCache)) {
                    accessTokenCache = getAppAccessToken();
                }
                return accessTokenCache.accessToken;
            }
        }
    }

    private static boolean isValidToken(AccessTokenCache accessTokenCache) {
        return accessTokenCache != null && ZonedDateTime.now().isBefore(accessTokenCache.expiredTime.minusMinutes(1));
    }

    private AccessTokenCache getAppAccessToken() {
        WeChatGetAccessTokenRequest getAccessTokenRequest = new WeChatGetAccessTokenRequest();
        getAccessTokenRequest.appId = accountConfig.appId;
        getAccessTokenRequest.secretKey = accountConfig.secretKey;
        HttpUriRequest request = RequestBuilder.get()
            .setUri(WeChatGetAccessTokenRequest.URL)
            .addParameter("grant_type", WeChatGetAccessTokenRequest.GRANT_TYPE)
            .addParameter("appid", getAccessTokenRequest.appId)
            .addParameter("secret", getAccessTokenRequest.secretKey)
            .build();
        WeChatAccessTokenResponse response;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            CloseableHttpResponse httpResponse = httpClient.execute(request);
            HttpEntity entity = httpResponse.getEntity();
            String s = EntityUtils.toString(entity);
            logger.info("Success get access_token from WeChat platform, response: {}", s);
            response = mapper.readValue(s, WeChatAccessTokenResponse.class);
        } catch (IOException e) {
            throw new WeChatIntegrationException("Failed to get access_token from WeChat platform.", e);
        }
        AccessTokenCache accessTokenCache = new AccessTokenCache();
        accessTokenCache.accessToken = response.accessToken;
        accessTokenCache.expiredTime = ZonedDateTime.now().plusSeconds(response.expireIn).minusMinutes(1);
        return accessTokenCache;
    }
}
