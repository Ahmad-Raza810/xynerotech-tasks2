package com.xynerotech.task.household_services_booking_platform.controller;

import com.xynerotech.task.household_services_booking_platform.dto.CreateHomeServiceDTO;
import com.xynerotech.task.household_services_booking_platform.dto.HomeServiceResponseDTO;
import com.xynerotech.task.household_services_booking_platform.dto.UpdateHomeServiceDTO;
import com.xynerotech.task.household_services_booking_platform.entities.HomeService;
import com.xynerotech.task.household_services_booking_platform.response.ApiResponse;
import com.xynerotech.task.household_services_booking_platform.service.HomeServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/api/service")
public class HomeServiceController {

    @Autowired
    private HomeServiceService homeServiceService;

    // Get all services
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<HomeServiceResponseDTO>>> getAllServices() {
        List<HomeService> services = homeServiceService.getAllServices();

        List<HomeServiceResponseDTO> dtos=services
                .stream()
                .map(HomeServiceResponseDTO::userToDto)
                .toList();

        ApiResponse<List<HomeServiceResponseDTO>> response = new ApiResponse<>(
                "Services fetched successfully",
                LocalDateTime.now(),
                dtos,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    // Get service by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<HomeService>> getServiceById(@PathVariable Long id) {
        HomeService service = homeServiceService.getServiceById(id);
        ApiResponse<HomeService> response = new ApiResponse<>(
                "Service found",
                LocalDateTime.now(),
                service,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    // Add new service
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<HomeServiceResponseDTO>> addService(@Valid @RequestBody CreateHomeServiceDTO dto) {
        HomeService savedService = homeServiceService.addService(CreateHomeServiceDTO.dtoToHomeService(dto));
        HomeServiceResponseDTO responseDTO=HomeServiceResponseDTO.userToDto(savedService);
        ApiResponse<HomeServiceResponseDTO> response = new ApiResponse<>(
                "Service added successfully",
                LocalDateTime.now(),
                responseDTO,
                true,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update service
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<HomeServiceResponseDTO>> updateService( @PathVariable Long id,
                                                                              @Valid @RequestBody UpdateHomeServiceDTO updatedService) {
        HomeService updated = homeServiceService.updateService(id, UpdateHomeServiceDTO.dtoToHomeService(updatedService));
        HomeServiceResponseDTO responseDTO=HomeServiceResponseDTO.userToDto(updated);
        ApiResponse<HomeServiceResponseDTO> response = new ApiResponse<>(
                "Service updated successfully",
                LocalDateTime.now(),
                responseDTO,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    // Delete service
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteService(@PathVariable Long id) {
        homeServiceService.deleteService(id);
        ApiResponse<Void> response = new ApiResponse<>(
                "Service deleted successfully",
                LocalDateTime.now(),
                null,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }
}
