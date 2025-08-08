package com.xynerotech.task.household_services_booking_platform.service;

import com.xynerotech.task.household_services_booking_platform.entities.HomeService;
import com.xynerotech.task.household_services_booking_platform.exception.DuplicateResourceException;
import com.xynerotech.task.household_services_booking_platform.exception.ResourceNotFoundException;
import com.xynerotech.task.household_services_booking_platform.repository.HomeServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class HomeServiceServiceImpl implements HomeServiceService{

    @Autowired
    private HomeServiceRepository homeServiceRepository;


    //service method for getting all services
    @Override
    public List<HomeService> getAllServices() {
        return homeServiceRepository.findAll();
    }

    //service method for getting a service by id
    @Override
    public HomeService getServiceById(Long id) {
       return  homeServiceRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException
                        ("service with id '"+id+"' not exist. "));
    }

    //service method for adding new service.
    @Override
    public HomeService addService(HomeService service) {
        if (homeServiceRepository.existsByName(service.getName())) {
            throw new DuplicateResourceException("Service already registered.");
        }
        return homeServiceRepository.save(service);
    }

    //service method for updating a service.
    @Override
    public HomeService updateService(Long id, HomeService updatedService) {
        HomeService returnedHomeService=homeServiceRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException
                        ("service with id '"+id+"' not exist. "));

        if (StringUtils.hasText(updatedService.getName()))
            returnedHomeService.setName(updatedService.getName());

        if (StringUtils.hasText(updatedService.getDescription()))
            returnedHomeService.setDescription(updatedService.getDescription());

        if (updatedService.getPrice() != null)
            returnedHomeService.setPrice(updatedService.getPrice());



        return homeServiceRepository.save(returnedHomeService);
    }


    //service method for deleting a service.
    @Override
    public void deleteService(Long id) {
        HomeService returnedHomeService=homeServiceRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException
                        ("service with id '"+id+"' not exist. "));

        homeServiceRepository.delete(returnedHomeService);
    }
}
