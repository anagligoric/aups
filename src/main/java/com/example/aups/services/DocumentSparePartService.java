package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.DocumentSparePart;
import com.example.aups.repositories.DocumentSparePartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentSparePartService {

    private DocumentSparePartRepository documentSparePartRepository;

    public DocumentSparePartService(DocumentSparePartRepository documentSparePartRepository) {
        this.documentSparePartRepository = documentSparePartRepository;
    }

    @Transactional(readOnly = true)
    public List<DocumentSparePart> getAllDocumentSpareParts() {
        return documentSparePartRepository.findAll();
    }

    @Transactional(readOnly = true)
    public DocumentSparePart getDocumentSparePartById(Long id) {
        return documentSparePartRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Document spare part with id " + id + " does not exist."));
    }

    public DocumentSparePart create(DocumentSparePart documentSparePart) {
        return documentSparePartRepository.save(documentSparePart);
    }


    public DocumentSparePart update(Long id, DocumentSparePart documentSparePart) {
        if (documentSparePartRepository.findById(id).isEmpty()) {
            throw new CustomException("Document spare part with id " + id + " does not exist.");
        }
        return documentSparePartRepository.save(documentSparePart);
    }

    public void delete(Long id) {
        if (documentSparePartRepository.findById(id).isEmpty()) {
            throw new CustomException("Document spare part with id " + id + " does not exist.");
        }
        documentSparePartRepository.deleteById(id);
    }
}

