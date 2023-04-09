package app.web.error;

/**
 * @author steve
 */
public class ConflictException  extends WebException {
    public ConflictException(String errorCode, String message) {
        super(WebErrorCodes.CONFLICT, errorCode, message);
    }
}
