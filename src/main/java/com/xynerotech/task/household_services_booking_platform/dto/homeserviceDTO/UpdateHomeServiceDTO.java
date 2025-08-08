package com.xynerotech.task.household_services_booking_platform.dto.homeserviceDTO;

import com.xynerotech.task.household_services_booking_platform.entities.HomeService;
import com.xynerotech.task.household_services_booking_platform.validation.AtLeastOneFieldNotEmpty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AtLeastOneFieldNotEmpty(message = "At least one field (name, desc, or price) must be provided.")
public class UpdateHomeServiceDTO {

    @Size(min = 3, max = 50, message = "Service name must be between 3 and 50 characters")
    private String name;

    @Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;



    public static HomeService dtoToHomeService(UpdateHomeServiceDTO dto) {
        HomeService homeService = new HomeService();

        if (dto.getName() != null)
            homeService.setName(dto.getName());

        if (dto.getDescription() != null)
            homeService.setDescription(dto.getDescription());

        if (dto.getPrice() != null)
            homeService.setPrice(dto.getPrice());

        return homeService;
    }

}

