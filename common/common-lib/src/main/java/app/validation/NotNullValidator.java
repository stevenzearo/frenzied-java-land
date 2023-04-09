package app.validation;

import app.validation.annotation.NotNull;
import app.web.error.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author steve
 */
public class NotNullValidator extends AbstractValidator {
    private static final Class<? extends Annotation> NOT_NULL_CLASS = NotNull.class;
    private static final Set<Class<?>> VALID_CLASSES = Set.of(Object.class);

    public NotNullValidator() {
        super(NOT_NULL_CLASS, VALID_CLASSES);
    }

    @Override
    protected void validateFieldValue(Field field, Object fieldValue) throws Exception {
        NotNull notNullAnnotation = field.getDeclaredAnnotation(NotNull.class);
        if (fieldValue == null) throw new ValidationException(notNullAnnotation.msg());
    }
}
