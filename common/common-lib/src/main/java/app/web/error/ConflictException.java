package app.web.error;

/**
 * @author steve
 */
public class ConflictException extends WebException {
    private static final String ERROR_CODE = "CONFLICT";

    public ConflictException(String errorCode, String message) {
        super(WebErrorCodes.CONFLICT, errorCode, message);
    }

    public ConflictException(String message) {
        super(WebErrorCodes.CONFLICT, ERROR_CODE, message);
    }
}
