package com.xynerotech.task.household_services_booking_platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PublicController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "🚀 Server is up and running smoothly!");
        response.put("timestamp", LocalDateTime.now());

        Map<String, Object> info = new HashMap<>();
        info.put("project", "Household Services Booking API");
        info.put("status", "Healthy");
        info.put("version", "1.0.0");
        info.put("author", "Ahmad Raza");

        response.put("serverInfo", info);
        response.put("tip", "You can register at /api/register or explore services at /api/services");

        return ResponseEntity.ok(response);
    }
}
