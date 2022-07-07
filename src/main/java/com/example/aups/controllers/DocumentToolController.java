package com.example.aups.controllers;

import com.example.aups.models.DocumentTool;
import com.example.aups.services.DocumentToolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/document/tool")
public class DocumentToolController {
    private final DocumentToolService documentToolService;

    public DocumentToolController(DocumentToolService documentToolService) {
        this.documentToolService = documentToolService;
    }

    @GetMapping
    public ResponseEntity<List<DocumentTool>> getAllDocumentTools() {
        return ResponseEntity.ok(documentToolService.getAllDocumentTools());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTool> getDocumentToolById(@PathVariable Long id) {
        return ResponseEntity.ok(documentToolService.getDocumentToolById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<DocumentTool> addDocumentTool(@RequestBody DocumentTool documentTool){
        documentToolService.create(documentTool);
        return ResponseEntity.ok(documentTool);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<DocumentTool> updateDocumentTool(@PathVariable("id") Long id, @RequestBody DocumentTool documentTool) {
        documentToolService.update(id, documentTool);
        return ResponseEntity.ok(documentTool);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocumentTool(@PathVariable("id") Long id) {
        documentToolService.delete(id);
    }
}
