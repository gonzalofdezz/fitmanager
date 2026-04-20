package com.gonzalofdezz.fitmanager.application.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UUIDValidator.class)
@Documented
public @interface ValidUUID {
    String message() default "El formato no es un UUID válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

