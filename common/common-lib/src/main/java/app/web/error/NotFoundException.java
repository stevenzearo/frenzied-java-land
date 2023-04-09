package app.web.error;

/**
 * @author steve
 */
public class NotFoundException extends WebException {
    private static final String ERROR_CODE = "NOT_FOUND";

    public NotFoundException(String message) {
        super(WebErrorCodes.NOT_FOUND, ERROR_CODE, message);
    }

    public NotFoundException(String errorCode, String message) {
        super(WebErrorCodes.NOT_FOUND, errorCode, message);
    }
}
