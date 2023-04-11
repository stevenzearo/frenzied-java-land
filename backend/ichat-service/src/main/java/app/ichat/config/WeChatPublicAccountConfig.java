package app.ichat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Steve Zou
 */
@Configuration
public class WeChatPublicAccountConfig {
    @Value("${wechat.account.appId}")
    public String appId;
    @Value("${wechat.account.secretKey}")
    public String secretKey;
}
