package app.web.error;

/**
 * @author steve
 */
public class UnAuthorizedException extends WebException {
    public UnAuthorizedException(String errorCode, String message) {
        super(WebErrorCodes.UNAUTHORIZED, errorCode, message);
    }
}
