package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.exceptions.UserDoesNotExistException;
import com.example.aups.models.Location;
import com.example.aups.models.User;
import com.example.aups.repositories.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional(readOnly = true)
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Location with id " + id + " does not exist."));
    }

    public Location create(Location location) {
        return locationRepository.save(location);
    }

    public Location update(Long id, Location location) {
        if (locationRepository.findById(id).isEmpty()) {
            throw new CustomException("Location with id " + id + " does not exist.");
        }
        return locationRepository.save(location);
    }

    public void delete(Long id) {
        if (locationRepository.findById(id).isEmpty()) {
            throw new CustomException("Location with id " + id + " does not exist.");
        }
        locationRepository.deleteById(id);
    }
}
