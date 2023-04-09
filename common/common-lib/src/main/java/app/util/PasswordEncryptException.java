package app.util;

import app.web.error.WebException;

/**
 * @author steve
 */
public class PasswordEncryptException extends WebException {
    private static final String ERROR_CODE = "PASSWORD_ENCRYPT_ERROR";

    public PasswordEncryptException(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }
}
