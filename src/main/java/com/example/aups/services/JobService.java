package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.Client;
import com.example.aups.models.Job;
import com.example.aups.repositories.ClientRepository;
import com.example.aups.repositories.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobService {

    private JobRepository jobRepository;
    private ClientRepository clientRepository;

    public JobService(JobRepository jobRepository, ClientRepository clientRepository) {

        this.jobRepository = jobRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Job with id " + id + " does not exist."));
    }

    public Job create(Job job) {
        job.setStatus(Job.Status.PENDING);
        Long clientId = job.getClient().getId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new CustomException("Client with id " + clientId + " does not exist."));
        job.setClient(client);
        return jobRepository.save(job);
    }


    public Job update(Long id, Job job) {
        if (jobRepository.findById(id).isEmpty()) {
            throw new CustomException("Job with id " + id + " does not exist.");
        }
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new CustomException("Job not found"));
        existingJob.setType(job.getType());
        existingJob.setDescription(job.getDescription());
        existingJob.setClient(job.getClient());
        Long clientId = job.getClient().getId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new CustomException("Client with id " + clientId + " does not exist."));
        existingJob.setClient(client);
        return jobRepository.save(existingJob);
    }

    public void delete(Long id) {
        if (jobRepository.findById(id).isEmpty()) {
            throw new CustomException("Job with id " + id + " does not exist.");
        }
        jobRepository.deleteById(id);
    }
}

