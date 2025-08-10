package com.xynerotech.task.household_services_booking_platform.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class LocalTimeValidator implements ConstraintValidator<ValidLocalTime, LocalTime> {

    @Override
    public boolean isValid(LocalTime time, ConstraintValidatorContext context) {
        if (time == null) return false;
        return true;
    }
}
