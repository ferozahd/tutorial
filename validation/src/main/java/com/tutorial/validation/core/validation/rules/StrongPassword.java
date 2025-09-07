package com.tutorial.validation.core.validation.rules;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface StrongPassword {

    String message() default "Password must contain at least one uppercase, one lowercase, one digit, and one special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}