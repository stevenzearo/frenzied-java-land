package app.site.web;

import app.web.error.WebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author steve
 */
@RestControllerAdvice
public class ErrorHandler {
    private final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(Throwable.class)
    public String handleError(Throwable throwable) {
        WebException webException = new WebException(throwable.getMessage());
        if (throwable instanceof WebException) {
            webException = (WebException) throwable;
        }
        logger.error(MarkerFactory.getMarker(webException.getErrorCode()), throwable.getMessage(), throwable);
        return "";
    }
}
