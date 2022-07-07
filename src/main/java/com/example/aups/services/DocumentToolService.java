package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.DocumentTool;
import com.example.aups.repositories.DocumentToolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentToolService {

    private DocumentToolRepository documentToolRepository;

    public DocumentToolService(DocumentToolRepository documentToolRepository) {
        this.documentToolRepository = documentToolRepository;
    }

    @Transactional(readOnly = true)
    public List<DocumentTool> getAllDocumentTools() {
        return documentToolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public DocumentTool getDocumentToolById(Long id) {
        return documentToolRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Document tools with id " + id + " does not exist."));
    }

    public DocumentTool create(DocumentTool documentTool) {
        return documentToolRepository.save(documentTool);
    }


    public DocumentTool update(Long id, DocumentTool documentTool) {
        if (documentToolRepository.findById(id).isEmpty()) {
            throw new CustomException("Document tools with id " + id + " does not exist.");
        }
        return documentToolRepository.save(documentTool);
    }

    public void delete(Long id) {
        if (documentToolRepository.findById(id).isEmpty()) {
            throw new CustomException("Document tools with id " + id + " does not exist.");
        }
        documentToolRepository.deleteById(id);
    }
}

