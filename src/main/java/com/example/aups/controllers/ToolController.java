package com.example.aups.controllers;

import com.example.aups.models.Tool;
import com.example.aups.services.ToolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tool")
public class ToolController {
    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    public ResponseEntity<List<Tool>> getAllTools() {
        return ResponseEntity.ok(toolService.getAllTools());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id) {
        return ResponseEntity.ok(toolService.getToolById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Tool> addTool(@RequestBody Tool tool){
        toolService.create(tool);
        return ResponseEntity.ok(tool);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Tool> updateTool(@PathVariable("id") Long id, @RequestBody Tool tool) {
        toolService.update(id, tool);
        return ResponseEntity.ok(tool);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTool(@PathVariable("id") Long id) {
        toolService.delete(id);
    }
}
