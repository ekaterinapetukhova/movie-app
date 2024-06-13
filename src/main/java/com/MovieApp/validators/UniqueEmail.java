package com.MovieApp.validators;

import jakarta.validation.*;

import java.lang.annotation.*;

@Constraint(validatedBy = { UniqueEmailValidator.class })
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueEmail {
    String message() default "Email is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
