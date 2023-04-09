package app.validation;

import app.web.error.UnsupportedValidationClassException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author steve
 */
abstract class AbstractValidator {
    protected final Class<? extends Annotation> validatorClass;
    protected final Set<Class<?>> validClasses;

    public AbstractValidator(Class<? extends Annotation> validatorClass, Set<Class<?>> validClasses) {
        this.validatorClass = validatorClass;
        this.validClasses = validClasses;
    }

    <T> void validate(Annotation annotation, Field field, T t) throws Exception {
        Object fieldValue = field.get(t);
        if (annotation.annotationType() != validatorClass) return;
        validateFieldClass(fieldValue);
        validateFieldValue(field, fieldValue);
    }

    private void validateFieldClass(Object fieldValue) throws Exception {
        if (validClasses.contains(Object.class)) return;
        Class<?> valueClass = fieldValue.getClass();
        if (!validClasses.contains(valueClass))
            throw new UnsupportedValidationClassException(String.format("unsupported validation class %s", valueClass.getCanonicalName()));
    }

    protected abstract void validateFieldValue(Field field, Object fieldValue) throws Exception;
}
