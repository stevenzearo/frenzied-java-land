package app.validation;

import app.validation.annotation.NotBlank;
import app.web.error.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author steve
 */
public class NotBlankValidator extends AbstractValidator {
    private static final Class<? extends Annotation> NOT_BLANK_CLASS = NotBlank.class;
    private static final Set<Class<?>> VALID_CLASSES = Set.of(String.class);

    public NotBlankValidator() {
        super(NOT_BLANK_CLASS, VALID_CLASSES);
    }

    @Override
    protected void validateFieldValue(Field field, Object fieldValue) throws Exception {
        NotBlank notNullAnnotation = field.getDeclaredAnnotation(NotBlank.class);
        String strFieldValue = fieldValue.toString();
        if (strFieldValue.trim().length() == 0) throw new ValidationException(notNullAnnotation.msg());
    }
}
