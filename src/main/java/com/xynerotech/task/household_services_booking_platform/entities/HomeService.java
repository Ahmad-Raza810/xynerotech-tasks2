package com.xynerotech.task.household_services_booking_platform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HomeService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Service name is required")
    @Size(min = 3, max = 50, message = "Service name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Service description is required")
    @Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @OneToMany(mappedBy = "service" ,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Booking> bookings;
}
