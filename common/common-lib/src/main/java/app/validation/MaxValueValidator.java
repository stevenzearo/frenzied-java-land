package app.validation;

import app.validation.annotation.Max;
import app.web.error.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author steve
 */
public class MaxValueValidator extends AbstractValidator {
    private static final Class<? extends Annotation> MAX_CLASS = Max.class;
    private static final Set<Class<?>> VALID_CLASSES = Set.of(Integer.class, Long.class, Double.class, Float.class);

    public MaxValueValidator() {
        super(MAX_CLASS, VALID_CLASSES);
    }

    @Override
    protected void validateFieldValue(Field field, Object fieldValue) throws Exception {
        double d = Double.parseDouble(fieldValue.toString());
        Max minAnnotation = field.getDeclaredAnnotation(Max.class);
        if (d > minAnnotation.value())
            throw new ValidationException(String.format(minAnnotation.msg(), d, minAnnotation.value()));
    }
}
