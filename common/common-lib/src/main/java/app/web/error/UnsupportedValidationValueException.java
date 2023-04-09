package app.web.error;

/**
 * @author steve
 */
public class UnsupportedValidationValueException extends WebException {
    private static final String ERROR_CODE = "UNSUPPORTED_VALIDATION_VALUE";

    public UnsupportedValidationValueException(String message) {
        super(WebErrorCodes.CONFLICT, ERROR_CODE, message);
    }
}
