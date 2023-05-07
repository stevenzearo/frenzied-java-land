package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author Steve Zou
 */
public class WeChatError {
    @JsonAlias("errcode")
    public String errorCode;
    @JsonAlias("errmsg")
    public String errorMsg;

    public enum ErrorCode {
        TOKEN_EXPIRED("42001"),
        TOKEN_INVALID("40001");
        public final String value;

        ErrorCode(String value) {
            this.value = value;
        }
    }

}
