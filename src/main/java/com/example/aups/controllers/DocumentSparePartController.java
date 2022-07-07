package com.example.aups.controllers;

import com.example.aups.models.DocumentSparePart;
import com.example.aups.services.DocumentSparePartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/document/spare-part")
public class DocumentSparePartController {
    private final DocumentSparePartService documentSparePartService;

    public DocumentSparePartController(DocumentSparePartService documentSparePartService) {
        this.documentSparePartService = documentSparePartService;
    }

    @GetMapping
    public ResponseEntity<List<DocumentSparePart>> getAllDocumentSpareParts() {
        return ResponseEntity.ok(documentSparePartService.getAllDocumentSpareParts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentSparePart> getDocumentSparePartById(@PathVariable Long id) {
        return ResponseEntity.ok(documentSparePartService.getDocumentSparePartById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<DocumentSparePart> addDocumentSparePart(@RequestBody DocumentSparePart documentSparePart){
        documentSparePartService.create(documentSparePart);
        return ResponseEntity.ok(documentSparePart);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<DocumentSparePart> updateDocumentSparePart(@PathVariable("id") Long id, @RequestBody DocumentSparePart documentSparePart) {
        documentSparePartService.update(id, documentSparePart);
        return ResponseEntity.ok(documentSparePart);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocumentSparePart(@PathVariable("id") Long id) {
        documentSparePartService.delete(id);
    }
}
