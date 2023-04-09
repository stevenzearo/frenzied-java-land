package app.validation;

import app.validation.annotation.Max;
import app.validation.annotation.Min;
import app.validation.annotation.NotBlank;
import app.validation.annotation.NotNull;
import app.validation.annotation.Size;

/**
 * @author steve
 */
public class AObject {
    @NotNull
    @NotBlank
    public String id;

    @NotBlank
    @Size(min = 2, max = 50)
    public String name;
    @Min(1)
    public Integer age;

    @Min(1.5)
    public Float wage;

    @NotNull
    public ABObject abObject;

    public AObject(String id, String name, Integer age, Float wage, ABObject abObject) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.wage = wage;
        this.abObject = abObject;
    }

    public static class ABObject {
        @NotNull
        @NotBlank
        public String id;

        @NotNull
        @NotBlank
        public String name;

        @NotNull
        @Min(0)
        @Max(100)
        public Float score;

    }
}
