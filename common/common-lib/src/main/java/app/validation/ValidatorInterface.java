package app.validation;

/**
 * @author steve
 */
public interface ValidatorInterface {
    <T> void validate(T t) throws Exception;
}
