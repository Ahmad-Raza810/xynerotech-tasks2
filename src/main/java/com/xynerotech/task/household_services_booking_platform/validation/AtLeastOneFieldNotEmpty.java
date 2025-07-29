package com.xynerotech.task.household_services_booking_platform.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneFieldValidator.class)
public @interface AtLeastOneFieldNotEmpty {
    String message() default "At least one field must be provided for update";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
