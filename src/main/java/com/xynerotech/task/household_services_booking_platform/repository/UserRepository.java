package com.xynerotech.task.household_services_booking_platform.repository;

import com.xynerotech.task.household_services_booking_platform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
