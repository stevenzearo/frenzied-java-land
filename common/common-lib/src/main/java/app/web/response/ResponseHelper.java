package app.web.response;

import app.web.error.ErrorResponse;
import app.web.error.WebErrorCodes;
import app.web.error.WebException;

/**
 * @author steve
 */
public final class ResponseHelper {
    public static <T> Response<T> ok() {
        return new Response<>(WebErrorCodes.OK, null, null);
    }

    public static <A> Response<A> okOf(A data) {
        return new Response<>(WebErrorCodes.OK, data, null);
    }

    public static <E extends WebException, T> Response<T> errorOf(E e) {
        return new Response<>(e.getStatusCode(), null, new ErrorResponse(e));
    }

    public static <T> Response<T> encloseWithException(SupplierWithoutReturn supplierWithoutReturn) {
        try {
            supplierWithoutReturn.call();
        } catch (Exception e) {
            return encloseException(e);
        }
        return ok();
    }

    public static <D> Response<D> encloseWithException(SupplierWithReturn<D> supplierWithReturn) {
        D data;
        try {
            data = supplierWithReturn.get();
        } catch (Exception e) {
            return encloseException(e);
        }
        return okOf(data);
    }

    private static <D> Response<D> encloseException(Exception e) {
        WebException webException = new WebException(e);
        if (e instanceof WebException) {
            webException = (WebException) e;
        }
        return new Response<>(webException.getStatusCode(), null, new ErrorResponse(webException));
    }

    public static <T> T fetchData(Response<T> response) {
        if (response.statusCode != WebErrorCodes.OK) {
            return null;
        }
        return response.data;
    }

    public static <T> T fetchDataWithException(Response<T> response) throws WebException {
        if (response.statusCode != WebErrorCodes.OK) {
            ErrorResponse error = response.error;
            throw new WebException(error.statusCode, error.errorCode, error.message);
        }
        return response.data;
    }

    public static void checkStatusCode(EmptyResponse response) throws WebException {
        ErrorResponse error = response.error;
        if (response.statusCode != WebErrorCodes.OK)
            throw new WebException(error.statusCode, error.errorCode, error.message);
    }
}
