package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.Team;
import com.example.aups.models.Vehicle;
import com.example.aups.repositories.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Vehicle with id " + id + " cannot be found"));
    }


    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }


    public Vehicle update(Long id, Vehicle vehicle) {
        if (vehicleRepository.findById(id).isEmpty()) {
            throw new CustomException("Vehicle with id " + id + " does not exist.");
        }
        return vehicleRepository.save(vehicle);
    }

    public void delete(Long id) {
        if (vehicleRepository.findById(id).isEmpty()) {
            throw new CustomException("Vehicle with id " + id + " does not exist.");
        }
        vehicleRepository.deleteById(id);
    }
}
