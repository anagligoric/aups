package com.example.aups.services;

import com.example.aups.enums.JobStatus;
import com.example.aups.exceptions.CustomException;
import com.example.aups.exceptions.UserDoesNotExistException;
import com.example.aups.models.Client;
import com.example.aups.models.Job;
import com.example.aups.models.JobDto;
import com.example.aups.models.User;
import com.example.aups.repositories.ClientRepository;
import com.example.aups.repositories.JobRepository;
import com.example.aups.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, ClientRepository clientRepository, UserRepository userRepository) {

        this.jobRepository = jobRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Job> getMyJobs(String username) {
        return jobRepository.findAllByUser_Email(username);
    }

    @Transactional(readOnly = true)
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Job with id " + id + " does not exist."));
    }

    public JobDto create(JobDto jobDto) {
        jobDto.setStatus(JobStatus.PENDING);
        Long clientId = jobDto.getClientId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new CustomException("Client with id " + clientId + " does not exist."));
        Long userId = jobDto.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException(userId));
        Job job = new Job(jobDto.getType(),
                jobDto.getDescription(),
                jobDto.getStatus(),
                client,
                user);
        jobRepository.save(job);
        return jobDto;
    }


    public JobDto update(Long id, JobDto jobDto) {
        if (jobRepository.findById(id).isEmpty()) {
            throw new CustomException("Job with id " + id + " does not exist.");
        }
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new CustomException("Job not found"));
        existingJob.setType(jobDto.getType());
        existingJob.setDescription(jobDto.getDescription());

        Long clientId = jobDto.getId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new CustomException("Client with id " + clientId + " does not exist."));
        existingJob.setClient(client);

        Long userId = jobDto.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException(userId));
        existingJob.setUser(user);
        jobRepository.save(existingJob);
        return jobDto;
    }

    public Job updateStatus(Long id, JobStatus jobStatus) {
        if (jobRepository.findById(id).isEmpty()) {
            throw new CustomException("Job with id " + id + " does not exist.");
        }
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new CustomException("Job not found"));
        existingJob.setStatus(jobStatus);
        return jobRepository.save(existingJob);
    }

    public void delete(Long id) {
        if (jobRepository.findById(id).isEmpty()) {
            throw new CustomException("Job with id " + id + " does not exist.");
        }
        jobRepository.deleteById(id);
    }
}

