package app.web.error;

import java.io.Serializable;

/**
 * @author steve
 */
public class WebException extends Exception implements Serializable {
    int statusCode = WebErrorCodes.SERVER_ERROR;
    String errorCode = "INTERNAL_ERROR";

    public WebException() {
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public WebException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public WebException(int statusCode, String errorCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
