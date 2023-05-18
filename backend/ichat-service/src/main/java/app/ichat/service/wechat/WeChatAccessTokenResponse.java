package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Steve Zou
 */
public class WeChatAccessTokenResponse {
    @JsonProperty(value = "access_token")
    public String accessToken;
    @JsonProperty(value = "expires_in")
    public Long expireIn;
}
