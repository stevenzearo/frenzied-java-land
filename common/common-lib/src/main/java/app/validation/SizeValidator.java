package app.validation;

import app.validation.annotation.Size;
import app.web.error.UnsupportedValidationValueException;
import app.web.error.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

/**
 * @author steve
 */
public class SizeValidator extends AbstractValidator {
    private static final Class<? extends Annotation> SIZE_CLASS = Size.class;
    private static final Set<Class<?>> VALID_CLASSES = Set.of(String.class, Collection.class);

    public SizeValidator() {
        super(SIZE_CLASS, VALID_CLASSES);
    }

    @Override
    protected void validateFieldValue(Field field, Object fieldValue) throws Exception {
        Size sizeAnnotation = field.getDeclaredAnnotation(Size.class);
        int size = 0;
        if (fieldValue.getClass() == String.class) {
            size = ((String) fieldValue).length();
        } else {
            size = ((Collection<?>) fieldValue).size();
        }
        if (sizeAnnotation.min() < -1)
            throw new UnsupportedValidationValueException(String.format("size min value can not less than -1, min = %d", sizeAnnotation.min()));
        if (sizeAnnotation.max() < -1)
            throw new UnsupportedValidationValueException(String.format("size max value can not less than -1, max = %d", sizeAnnotation.max()));
        if (sizeAnnotation.min() > sizeAnnotation.max())
            throw new UnsupportedValidationValueException(String.format("size max value can not less than size min value, min = %d, max = %d", sizeAnnotation.min(), sizeAnnotation.max()));
        if (sizeAnnotation.min() != -1 && size < sizeAnnotation.min())
            throw new ValidationException(String.format("field value min size can not less than size min value %d, size = %d", sizeAnnotation.min(), size));
        if (sizeAnnotation.max() != -1 && size > sizeAnnotation.max())
            throw new ValidationException(String.format("field value min size can not greater than size max value %d, size = %d", sizeAnnotation.max(), size));
    }
}
