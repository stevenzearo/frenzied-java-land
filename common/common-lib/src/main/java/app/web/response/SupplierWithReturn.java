package app.web.response;

/**
 * @author steve
 */
@FunctionalInterface
public interface SupplierWithReturn<T> {
    T get() throws Exception;
}
