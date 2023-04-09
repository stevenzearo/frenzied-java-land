package app.web.error;

/**
 * @author steve
 */
public class ValidationException  extends WebException {
    private static final String ERROR_CODE = "VALIDATION_ERROR";

    public ValidationException(String message) {
        super(WebErrorCodes.CONFLICT, ERROR_CODE, message);
    }
}