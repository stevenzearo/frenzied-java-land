package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author Steve Zou
 */
public class WeChatError {
    @JsonAlias("error_code")
    public String errorCode;
    @JsonAlias("errormsg")
    public String errorMsg;
}
