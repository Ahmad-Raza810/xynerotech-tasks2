package com.xynerotech.task.household_services_booking_platform.controller;

import com.xynerotech.task.household_services_booking_platform.dto.LoginRequestDTO;
import com.xynerotech.task.household_services_booking_platform.dto.LoginResponseDTO;
import com.xynerotech.task.household_services_booking_platform.dto.RegisterUserDTO;
import com.xynerotech.task.household_services_booking_platform.dto.UserResponseDTO;
import com.xynerotech.task.household_services_booking_platform.entities.AppUser;
import com.xynerotech.task.household_services_booking_platform.response.ApiResponse;
import com.xynerotech.task.household_services_booking_platform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(@RequestBody @Valid RegisterUserDTO dto) {

        AppUser user = userService.registerUser(RegisterUserDTO.dtoToUser(dto));
        UserResponseDTO registerUserDTO=UserResponseDTO.userToResponseDto(user);

        ApiResponse<UserResponseDTO> response = new ApiResponse<>(
                "User registered successfully",
                LocalDateTime.now(),
                registerUserDTO,
                true,
                HttpStatus.CREATED.value()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody @Valid LoginRequestDTO dto) {
        LoginResponseDTO loginResponse = userService.loginUser(dto);


        ApiResponse<LoginResponseDTO> response = new ApiResponse<>(
                "Login successful",
                LocalDateTime.now(),
                loginResponse,
                true,
                HttpStatus.OK.value()
        );

        return ResponseEntity.ok(response);
    }

}

