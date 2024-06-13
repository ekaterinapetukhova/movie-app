package com.MovieApp.validators;

import java.lang.annotation.*;

import jakarta.validation.*;

@Constraint(validatedBy = { UniqueUsernameValidator.class })
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueUsername {
    String message() default "Username is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
