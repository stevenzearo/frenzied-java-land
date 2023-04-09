package app.validation;

/**
 * @author steve
 */

/*
 * for set restriction which classes need need do validation check
 * */
@FunctionalInterface
public interface ValidatorRestriction {
    <T> boolean restrict(T t);
}
