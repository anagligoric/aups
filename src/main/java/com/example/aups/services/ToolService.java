package com.example.aups.services;
import com.example.aups.exceptions.CustomException;
import com.example.aups.models.Tool;
import com.example.aups.repositories.ToolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ToolService {

    private ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Transactional(readOnly = true)
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    public Tool findById(Long id) {
        return toolRepository.findById(id).orElseThrow(() -> new CustomException("Tool with id " + id + " does not exist"));
    }

    public Tool create(Tool tool) {
        return toolRepository.save(tool);
    }


    @Transactional
    public Tool update(Long id, Tool tool) {
        if (toolRepository.findById(id).isEmpty()) {
            throw new CustomException("Tool with id " + id + " does not exist.");
        }
        return toolRepository.save(tool);
    }

    @Transactional
    public void delete(Long id) {
        if (toolRepository.findById(id).isEmpty()) {
            throw new CustomException("Tool with id " + id + " does not exist.");
        }
        toolRepository.deleteById(id);
    }

}

