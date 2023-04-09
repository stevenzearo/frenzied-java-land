package app.web.error;

/**
 * @author steve
 */
public class MethodNotAllowedException extends WebException {
    public MethodNotAllowedException(String message) {
        super(WebErrorCodes.NOT_ALLOWED, message);
    }

    public MethodNotAllowedException(String errorCode, String message) {
        super(WebErrorCodes.NOT_ALLOWED, errorCode, message);
    }
}
