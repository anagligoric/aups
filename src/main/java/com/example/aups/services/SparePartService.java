package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.SparePart;
import com.example.aups.repositories.SparePartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SparePartService {

    private SparePartRepository sparePartRepository;

    public SparePartService(SparePartRepository sparePartRepository) {
        this.sparePartRepository = sparePartRepository;
    }

    @Transactional(readOnly = true)
    public List<SparePart> getAllSpareParts() {
        return sparePartRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SparePart getSparePartById(Long id) {
        return sparePartRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Spare part with id " + id + " does not exist."));
    }

    public SparePart create(SparePart sparePart) {
        return sparePartRepository.save(sparePart);
    }


    public SparePart update(Long id, SparePart sparePart) {
        if (sparePartRepository.findById(id).isEmpty()) {
            throw new CustomException("Spare part with id " + id + " does not exist.");
        }
        return sparePartRepository.save(sparePart);
    }

    public void delete(Long id) {
        if (sparePartRepository.findById(id).isEmpty()) {
            throw new CustomException("Spare part with id " + id + " does not exist.");
        }
        sparePartRepository.deleteById(id);
    }
}
