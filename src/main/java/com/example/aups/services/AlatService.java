package com.example.aups.services;
import com.example.aups.models.Alat;
import com.example.aups.repositories.AlatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlatService {

    private AlatRepository alatRepository ;

    public AlatService(AlatRepository alatRepository) {
        this.alatRepository = alatRepository;
    }

    @Transactional(readOnly = true)
    public List<Alat> getAllAlat() {
        return alatRepository.findAll();
    }
}

