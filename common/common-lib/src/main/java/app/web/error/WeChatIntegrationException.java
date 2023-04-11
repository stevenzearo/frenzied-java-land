package app.web.error;

/**
 * @author Steve Zou
 */
public class WeChatIntegrationException extends WebException {
    private static final String ERROR_CODE = "WECHAT_INTEGRATION_ERROR";

    public WeChatIntegrationException() {
        super(WebErrorCodes.CONFLICT, ERROR_CODE, "WeChat integration error.");
    }

    public WeChatIntegrationException(String errorCode, String message) {
        super(WebErrorCodes.CONFLICT, errorCode, message);
    }

    public WeChatIntegrationException(String message) {
        super(WebErrorCodes.CONFLICT, ERROR_CODE, message);
    }

    public WeChatIntegrationException(Throwable cause) {
        super(cause);
    }

    public WeChatIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeChatIntegrationException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public WeChatIntegrationException(int statusCode, String message) {
        super(statusCode, message);
    }

    public WeChatIntegrationException(int statusCode, String errorCode, String message) {
        super(statusCode, errorCode, message);
    }

}
