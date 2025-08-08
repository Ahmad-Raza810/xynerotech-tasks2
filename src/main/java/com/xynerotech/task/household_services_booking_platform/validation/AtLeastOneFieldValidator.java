package com.xynerotech.task.household_services_booking_platform.validation;

import com.xynerotech.task.household_services_booking_platform.dto.homeserviceDTO.UpdateHomeServiceDTO;
import com.xynerotech.task.household_services_booking_platform.dto.appuserDTO.UpdateUserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class AtLeastOneFieldValidator implements ConstraintValidator<AtLeastOneFieldNotEmpty, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof UpdateUserDTO dto) {
            return StringUtils.hasText(dto.getUserName()) ||
                    StringUtils.hasText(dto.getEmail()) ||
                    StringUtils.hasText(dto.getPassword());
        } else if (value instanceof UpdateHomeServiceDTO dto) {
            return StringUtils.hasText(dto.getName()) ||
                    StringUtils.hasText(dto.getDescription()) ||
                    dto.getPrice() != null;
        }

        return false; // unsupported object type
    }
}
