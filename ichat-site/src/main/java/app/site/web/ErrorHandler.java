package app.site.web;

import app.web.error.WebException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author steve
 */
@RestControllerAdvice
public class ErrorHandler {
    private static final String WECHAT_REQUEST_PATH = "/hland/home";
    private final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
    @Autowired
    ObjectMapper mapper;

    @ExceptionHandler(Throwable.class)
    public void handleError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
        WebException webException = new WebException(throwable.getMessage());
        if (throwable instanceof WebException) {
            webException = (WebException) throwable;
        }
        logger.error(MarkerFactory.getMarker(webException.getErrorCode()), throwable.getMessage(), throwable);
        if (request.getRemoteHost().contains("ichat-service")) {
            return;
        }
        if (WECHAT_REQUEST_PATH.equals(request.getRequestURI())) {
            rewriteResponse(response, HttpStatus.OK.value(), "", ContentType.TEXT_PLAIN.getMimeType());
        } else {
            rewriteResponse(response, webException.getStatusCode(), webException.getMessage(), ContentType.TEXT_PLAIN.getMimeType());
        }
    }

    private static void rewriteResponse(HttpServletResponse response, int status, String content, String contentType) {
        response.reset();
        CROSFilter.setResponseHeader(response);
        response.setStatus(status);
        response.setContentType(contentType);
        try (PrintWriter writer = response.getWriter()){
            writer.write(content);
        } catch (IOException e) {
            throw new WebException(e);
        }
    }
}
