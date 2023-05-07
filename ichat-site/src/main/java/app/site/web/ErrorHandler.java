package app.site.web;

import app.web.error.WebException;
import javax.servlet.http.HttpServletRequest;
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
    private static final String WECHAT_REQUEST_PATH = "/hland/home";
    private final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(Throwable.class)
    public String handleError(HttpServletRequest request, Throwable throwable) {
        WebException webException = new WebException(throwable.getMessage());
        if (throwable instanceof WebException) {
            webException = (WebException) throwable;
        }
        if (WECHAT_REQUEST_PATH.equals(request.getRequestURI())) {
            logger.error(MarkerFactory.getMarker(webException.getErrorCode()), throwable.getMessage(), throwable);
            return "";
        } else {
            throw webException;
        }
    }
}
