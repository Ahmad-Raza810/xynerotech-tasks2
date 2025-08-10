package com.xynerotech.task.household_services_booking_platform.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocalTimeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLocalTime {
    String message() default "Time must be in 24-hour format HH:mm";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
