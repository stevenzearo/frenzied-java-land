package app.validation;

import org.junit.jupiter.api.Test;

/**
 * @author steve
 */
public class ValidationTest {

    @Test
    void test() throws Exception {
        AObject.ABObject abObject = new AObject.ABObject();
        abObject.id = "id-0001";
        abObject.name = "world";
        abObject.score = 89.99f;
        AObject hello = new AObject("id-0001", "hello", 11, 15.2f, abObject);
        ValidatorInterface validator = new ValidatorImpl(ValidationTest::restrict);
        validator.validate(hello);
    }

    private static boolean restrict(Object o) {
        return o.getClass().getCanonicalName().startsWith("app");
    }
}
