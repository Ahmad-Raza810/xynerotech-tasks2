package com.xynerotech.task.household_services_booking_platform.dto.homeserviceDTO;

import com.xynerotech.task.household_services_booking_platform.entities.HomeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeServiceResponseDTO {

    private Long id;
    private String name;
    private String description;

    public static HomeServiceResponseDTO userToDto(HomeService homeService) {
        HomeServiceResponseDTO dto = new HomeServiceResponseDTO();
        dto.setId(homeService.getId());
        dto.setName(homeService.getName());
        dto.setDescription(homeService.getDescription());

        return dto;
    }
}
