package app.web.error;

/**
 * @author steve
 */
public class ForbiddenException extends WebException {
    public ForbiddenException(String errorCode, String message) {
        super(WebErrorCodes.FORBIDDEN, errorCode, message);
    }
}
