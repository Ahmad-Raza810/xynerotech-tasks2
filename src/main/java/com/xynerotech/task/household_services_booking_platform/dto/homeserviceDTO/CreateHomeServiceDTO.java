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


    public static HomeService dtoToHomeService(CreateHomeServiceDTO dto) {
        HomeService homeService=new HomeService();
        homeService.setName(dto.getName());
        homeService.setDescription(dto.getDescription());
        return homeService;

    }

    public static CreateHomeServiceDTO homeServiceTODto(HomeService homeService) {
        CreateHomeServiceDTO dto=new CreateHomeServiceDTO();
        dto.setName(homeService.getName());
        dto.setDescription(homeService.getDescription());
        return dto;

    }


}

