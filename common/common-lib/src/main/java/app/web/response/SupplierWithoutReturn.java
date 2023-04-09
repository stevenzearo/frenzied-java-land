package app.web.response;

/**
 * @author steve
 */
@FunctionalInterface
public interface SupplierWithoutReturn {
    void call() throws Exception;
}
