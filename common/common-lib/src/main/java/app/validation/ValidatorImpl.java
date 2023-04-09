package app.validation;

import app.validation.annotation.Max;
import app.validation.annotation.Min;
import app.validation.annotation.NotBlank;
import app.validation.annotation.NotNull;
import app.validation.annotation.Size;
import app.validation.annotation.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author steve
 */
public class ValidatorImpl implements ValidatorInterface {
    private final ValidatorRestriction restriction;
    Map<Class<? extends Annotation>, AbstractValidator> validatorMap = Map.of(
        Min.class, new MinValueValidator(),
        Max.class, new MaxValueValidator(),
        NotNull.class, new NotNullValidator(),
        NotBlank.class, new NotBlankValidator(),
        Size.class, new SizeValidator()
    );

    public ValidatorImpl(ValidatorRestriction restriction) {
        this.restriction = restriction;
    }

    @Override
    public <T> void validate(T t) throws Exception {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field f : declaredFields) {
            for (Annotation a : f.getDeclaredAnnotations()) {
                Class<? extends Annotation> annotationType = a.annotationType();
                if (annotationType.getDeclaredAnnotation(Validator.class) == null) continue;
                AbstractValidator validator = validatorMap.get(annotationType);
                validator.validate(a, f, t);
            }
            Object fieldValue = f.get(t);
            if (!restriction.restrict(fieldValue)) continue;
            validate(fieldValue);
        }
    }
}
