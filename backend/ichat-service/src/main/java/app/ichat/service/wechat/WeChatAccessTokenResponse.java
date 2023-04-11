package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author Steve Zou
 */
public class WeChatAccessTokenResponse {
    @JsonAlias(value = "access_token")
    public String accessToken;

    @JsonAlias(value = "expires_in")
    public Long expireIn;
}
