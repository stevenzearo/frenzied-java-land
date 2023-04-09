package app.util;

import app.web.error.WebException;

/**
 * @author steve
 */
public class EncryptException extends WebException {
    private static final String ERROR_CODE = "ENCRYPT_ERROR";

    public EncryptException(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }
}
