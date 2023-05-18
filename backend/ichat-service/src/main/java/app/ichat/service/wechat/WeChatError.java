package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Steve Zou
 */
public class WeChatError {
    @JsonProperty("errcode")
    public String errorCode;
    @JsonProperty("errmsg")
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
