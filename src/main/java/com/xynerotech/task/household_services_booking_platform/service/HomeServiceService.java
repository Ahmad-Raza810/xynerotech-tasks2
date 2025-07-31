package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.entities.HomeService;

import java.util.List;

public interface HomeServiceService {
    List<HomeService> getAllServices();
    HomeService getServiceById(Long id);
    HomeService addService(HomeService service);
    HomeService updateService(Long id, HomeService updatedService);
    void deleteService(Long id);
}
