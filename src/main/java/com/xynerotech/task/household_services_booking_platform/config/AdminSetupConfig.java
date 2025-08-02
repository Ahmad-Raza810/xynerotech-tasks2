package com.xynerotech.task.household_services_booking_platform.config;

import com.xynerotech.task.household_services_booking_platform.entities.AppUser;
import com.xynerotech.task.household_services_booking_platform.entities.Role;
import com.xynerotech.task.household_services_booking_platform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSetupConfig {

    @Bean
    public CommandLineRunner createDefaultAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "admin@xynerotech.com";

            // if admin not exists already
            if (!userRepository.existsByEmail(adminEmail)) {
                AppUser admin = new AppUser();
                admin.setUserName("Admin");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("admin123")); // ⚠️ change this later in production
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);
                System.out.println("✅ Default admin created");
            } else {
                System.out.println("ℹ️ Admin already exists");
            }
        };
    }
}

