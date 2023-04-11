package app.ichat.service.wechat;

/**
 * @author Steve Zou
 */
public class WeChatGetAccessTokenRequest {
    public static final String URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String GRANT_TYPE = "client_credential";
    public String appId;
    public String secretKey;
}
