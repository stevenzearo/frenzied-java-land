package app.web.error;

import java.io.Serializable;

/**
 * @author steve
 */
public class ErrorResponse implements Serializable {
    public int statusCode;
    public String errorCode;
    public String message;

    public ErrorResponse() {
    }

    public ErrorResponse(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorResponse(WebException exception) {
        this.statusCode = exception.getStatusCode();
        this.errorCode = exception.getErrorCode();
        this.message = exception.getMessage();
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
            "statusCode=" + statusCode +
            ", errorCode='" + errorCode + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
