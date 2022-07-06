package com.example.aups.controllers;

import com.example.aups.models.Alat;
import com.example.aups.models.Lokacija;
import com.example.aups.services.AlatService;
import com.example.aups.services.LokacijaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/lokacija")
public class LokacijaController {
    private final LokacijaService lokacijaService;

    public LokacijaController(LokacijaService lokacijaService) {
        this.lokacijaService = lokacijaService;
    }

    @GetMapping
    public ResponseEntity<List<Lokacija>> getAllAlat() {
        return ResponseEntity.ok(lokacijaService.getAllLokacija());
    }

}
