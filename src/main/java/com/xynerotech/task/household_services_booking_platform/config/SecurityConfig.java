package com.xynerotech.task.household_services_booking_platform.config;

import com.xynerotech.task.household_services_booking_platform.security.CustomAccessDeniedHandler;
import com.xynerotech.task.household_services_booking_platform.security.CustomAuthenticationEntryPoint;
import com.xynerotech.task.household_services_booking_platform.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(
                                "/api/health",
                                "/api/register",
                                "/api/login",
                                "/api/service/get",
                                "/api/service/get/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // User-only booking route — keep BEFORE admin route
                        .requestMatchers(HttpMethod.POST, "/api/user/*/bookings/add").hasRole("USER")

                        // Admin-only routes
                        .requestMatchers("/api/user/**", "/api/service/**","/api/bookings/**").hasRole("ADMIN")

                        // Everything else
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(customAccessDeniedHandler)          // 403 handler
                        .authenticationEntryPoint(customAuthenticationEntryPoint) // 401 handler
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
