package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.Client;
import com.example.aups.models.Document;
import com.example.aups.models.Job;
import com.example.aups.repositories.ClientRepository;
import com.example.aups.repositories.DocumentRepository;
import com.example.aups.repositories.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService {

    private DocumentRepository documentRepository;
    private JobRepository jobRepository;
    private ClientRepository clientRepository;

    public DocumentService(DocumentRepository documentRepository, JobRepository jobRepository, ClientRepository clientRepository ) {
        this.documentRepository = documentRepository;
        this.jobRepository = jobRepository;
        this.clientRepository = clientRepository;
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
        Long jobId = document.getJob().getId();
        Job job= jobRepository.findById(jobId)
                .orElseThrow(() -> new CustomException("Job with id " + jobId + " does not exist."));
        document.setJob(job);
        return documentRepository.save(document);
    }


    public Document update(Long id, Document document) {
        if (documentRepository.findById(id).isEmpty()) {
            throw new CustomException("Document with id " + id + " does not exist.");
        }
        Document existingDocument = documentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Document not found"));
        existingDocument.setCreationDate(document.getCreationDate());
        existingDocument.setNumber(document.getNumber());
        existingDocument.setPrice(document.getPrice());
        Long jobId = document.getJob().getId();
        Job job= jobRepository.findById(jobId)
                .orElseThrow(() -> new CustomException("Job with id " + jobId + " does not exist."));
        Client client = clientRepository.findById(job.getClient().getId())
                .orElseThrow(() -> new CustomException("Client with id " + job.getClient().getId()+ " does not exist."));
        job.setClient(client);
        existingDocument.setJob(job);
        return documentRepository.save(document);
    }

    public void delete(Long id) {
        if (documentRepository.findById(id).isEmpty()) {
            throw new CustomException("Document with id " + id + " does not exist.");
        }
        documentRepository.deleteById(id);
    }
}

