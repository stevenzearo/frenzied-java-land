package app.web.error;

/**
 * @author Steve Zou
 */
public class TuringIntegrationException extends WebException {
    private static final String ERROR_CODE = "TURING_INTEGRATION_ERROR";

    public TuringIntegrationException() {
        super(WebErrorCodes.TURING_INTEGRATION_ERROR, ERROR_CODE, "Turing integration error.");
    }

    public TuringIntegrationException(String errorCode, String message) {
        super(WebErrorCodes.TURING_INTEGRATION_ERROR, errorCode, message);
    }

    public TuringIntegrationException(String message) {
        super(WebErrorCodes.TURING_INTEGRATION_ERROR, ERROR_CODE, message);
    }

    public TuringIntegrationException(Throwable cause) {
        super(cause);
    }

    public TuringIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TuringIntegrationException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public TuringIntegrationException(int statusCode, String message) {
        super(statusCode, message);
    }

    public TuringIntegrationException(int statusCode, String errorCode, String message) {
        super(statusCode, errorCode, message);
    }

}
