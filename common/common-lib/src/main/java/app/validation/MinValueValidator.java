package app.validation;

import app.validation.annotation.Min;
import app.web.error.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author steve
 */
class MinValueValidator extends AbstractValidator {
    private static final Class<? extends Annotation> MIN_CLASS = Min.class;
    private static final Set<Class<?>> VALID_CLASSES = Set.of(Integer.class, Long.class, Double.class, Float.class);

    public MinValueValidator() {
        super(MIN_CLASS, VALID_CLASSES);
    }

    @Override
    protected void validateFieldValue(Field field, Object fieldValue) throws ValidationException {
        double d = Double.parseDouble(fieldValue.toString());
        Min minAnnotation = field.getDeclaredAnnotation(Min.class);
        if (d < minAnnotation.value())
            throw new ValidationException(String.format(minAnnotation.msg(), d, minAnnotation.value()));
    }
}
