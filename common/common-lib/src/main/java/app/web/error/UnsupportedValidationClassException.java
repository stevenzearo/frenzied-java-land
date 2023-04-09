package app.web.error;

/**
 * @author steve
 */
public class UnsupportedValidationClassException extends WebException {
    private static final String ERROR_CODE = "UNSUPPORTED_VALIDATION_CLASS";

    public UnsupportedValidationClassException(String message) {
        super(WebErrorCodes.CONFLICT, ERROR_CODE, message);
    }
}
