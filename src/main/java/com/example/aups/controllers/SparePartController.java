package com.example.aups.controllers;

import com.example.aups.models.SparePart;
import com.example.aups.services.SparePartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/spare/parts")
public class SparePartController {
    private final SparePartService sparePartService;

    public SparePartController(SparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }

    @GetMapping
    public ResponseEntity<List<SparePart>> getAllSpareParts() {
        return ResponseEntity.ok(sparePartService.getAllSpareParts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SparePart> getSparePartById(@PathVariable Long id) {
        return ResponseEntity.ok(sparePartService.getSparePartById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SparePart> addSparePart(@RequestBody SparePart sparePart){
        sparePartService.create(sparePart);
        return ResponseEntity.ok(sparePart);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SparePart> updateSparePart(@PathVariable("id") Long id, @RequestBody SparePart sparePart) {
        sparePartService.update(id, sparePart);
        return ResponseEntity.ok(sparePart);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSparePart(@PathVariable("id") Long id) {
        sparePartService.delete(id);
    }
}
