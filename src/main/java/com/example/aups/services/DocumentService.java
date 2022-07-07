package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.Document;
import com.example.aups.repositories.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService {

    private DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Transactional(readOnly = true)
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Document with id " + id + " does not exist."));
    }

    public Document create(Document document) {
        return documentRepository.save(document);
    }


    public Document update(Long id, Document document) {
        if (documentRepository.findById(id).isEmpty()) {
            throw new CustomException("Document with id " + id + " does not exist.");
        }
        return documentRepository.save(document);
    }

    public void delete(Long id) {
        if (documentRepository.findById(id).isEmpty()) {
            throw new CustomException("Document with id " + id + " does not exist.");
        }
        documentRepository.deleteById(id);
    }
}

