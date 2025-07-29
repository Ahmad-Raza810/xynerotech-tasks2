package com.xynerotech.task.household_services_booking_platform.validation;

import com.xynerotech.task.household_services_booking_platform.dto.UserUpdateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class AtLeastOneFieldValidator implements ConstraintValidator<AtLeastOneFieldNotEmpty, UserUpdateDTO> {

    @Override
    public boolean isValid(UserUpdateDTO userUpdateDTO, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(userUpdateDTO.getUserName()) ||
                StringUtils.hasText(userUpdateDTO.getEmail()) ||
                StringUtils.hasText(userUpdateDTO.getPassword());
    }
}
