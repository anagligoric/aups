package com.example.aups.controllers;

import com.example.aups.models.Alat;
import com.example.aups.services.AlatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/alat")
public class AlatController {
    private final AlatService alatService;

    public AlatController(AlatService alatService) {
        this.alatService = alatService;
    }

    @GetMapping
    public ResponseEntity<List<Alat>> getAllAlat() {
        return ResponseEntity.ok(alatService.getAllAlat());
    }
}
