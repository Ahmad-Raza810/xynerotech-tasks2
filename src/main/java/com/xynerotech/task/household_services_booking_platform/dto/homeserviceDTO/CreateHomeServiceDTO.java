package com.xynerotech.task.household_services_booking_platform.dto.homeserviceDTO;

import com.xynerotech.task.household_services_booking_platform.entities.HomeService;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateHomeServiceDTO {

    @NotBlank(message = "Service name is required")
    @Size(min = 3, max = 50, message = "Service name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Service description is required")
    @Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    public static HomeService dtoToHomeService(CreateHomeServiceDTO dto) {
        HomeService homeService=new HomeService();
        homeService.setName(dto.getName());
        homeService.setDescription(dto.getDescription());
        homeService.setPrice(dto.getPrice());

        return homeService;

    }

    public static CreateHomeServiceDTO homeServiceTODto(HomeService homeService) {
        CreateHomeServiceDTO dto=new CreateHomeServiceDTO();
        dto.setName(homeService.getName());
        dto.setDescription(homeService.getDescription());
        dto.setPrice(homeService.getPrice());

        return dto;

    }


}

