package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.*;
import com.example.aups.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final JobRepository jobRepository;
    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;
    private final ToolRepository toolRepository;

    public DocumentService(DocumentRepository documentRepository, JobRepository jobRepository, VehicleRepository vehicleRepository, ClientRepository clientRepository, ToolRepository toolRepository) {
        this.documentRepository = documentRepository;
        this.jobRepository = jobRepository;
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
        this.toolRepository = toolRepository;
    }

    @Transactional(readOnly = true)
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Document> getMyDocuments(String email) {
        return documentRepository.findAllByJob_User_Email(email);
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
        Long vehicleId = document.getVehicle().getId();
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new CustomException("Vehicle with id " + jobId + " does not exist."));
        document.setVehicle(vehicle);
        Set<Tool> tools = new HashSet<>();
        for (Tool tool : document.getTools()) {
            tools.add(toolRepository.findById(tool.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Tool not found")));
        }
        document.setTools(tools);
        return documentRepository.save(document);
    }


    public Document update(Long id, Document document) {
        if (documentRepository.findById(id).isEmpty()) {
            throw new CustomException("Document with id " + id + " does not exist.");
        }
        Document existingDocument = documentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Document not found"));
        existingDocument.setNumber(document.getNumber());
        existingDocument.setPrice(document.getPrice());
            Long jobId = document.getJob().getId();
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new CustomException("Job with id " + jobId + " does not exist."));
        Client client = clientRepository.findById(job.getClient().getId())
                .orElseThrow(() -> new CustomException("Client with id " + job.getClient().getId()+ " does not exist."));
        job.setClient(client);
        existingDocument.setJob(job);
        Set<Tool> tools = new HashSet<>();
        for (Tool tool : document.getTools()) {
            tools.add(toolRepository.findById(tool.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Tool not found")));
        }
        existingDocument.setTools(tools);
        return documentRepository.save(existingDocument);
    }

    public void delete(Long id) {
        if (documentRepository.findById(id).isEmpty()) {
            throw new CustomException("Document with id " + id + " does not exist.");
        }
        documentRepository.deleteById(id);
    }
}

