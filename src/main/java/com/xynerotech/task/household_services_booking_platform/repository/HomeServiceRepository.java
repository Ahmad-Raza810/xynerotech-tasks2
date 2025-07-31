package com.xynerotech.task.household_services_booking_platform.repository;

import com.xynerotech.task.household_services_booking_platform.entities.HomeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeServiceRepository extends JpaRepository<HomeService, Long> {
}

