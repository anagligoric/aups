package com.example.aups.services;

import com.example.aups.models.Lokacija;
import com.example.aups.repositories.LokacijaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LokacijaService {

    private LokacijaRepository lokacijaRepository ;

    public LokacijaService(LokacijaRepository lokacijaRepository) {
        this.lokacijaRepository = lokacijaRepository;
    }

    @Transactional(readOnly = true)
    public List<Lokacija> getAllLokacija() {
        return lokacijaRepository.findAll();
    }
}
