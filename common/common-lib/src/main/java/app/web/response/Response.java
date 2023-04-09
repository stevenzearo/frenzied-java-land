package app.web.response;

import app.web.error.ErrorResponse;

/**
 * @author steve
 */
public class Response<T> extends EmptyResponse {
    public T data;

    public Response() {
    }

    public Response(int statusCode, T data, ErrorResponse error) {
        super(statusCode, error);
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
            "data=" + data +
            ", statusCode=" + statusCode +
            ", error=" + error +
            '}';
    }
}
