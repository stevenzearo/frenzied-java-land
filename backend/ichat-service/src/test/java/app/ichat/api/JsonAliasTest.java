package app.ichat.api;

import app.ichat.service.wechat.WeChatAccessTokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * @author Steve Zou
 */

public class JsonAliasTest {

    @Test
    public void testJsonAlias() {
        String s = "{\"access_token\":\"abc\",\"expires_in\":123}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            WeChatAccessTokenResponse response = mapper.readValue(s, WeChatAccessTokenResponse.class);
            Assertions.assertEquals("abc", response.accessToken);
            Assertions.assertEquals(123L, response.expireIn);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
