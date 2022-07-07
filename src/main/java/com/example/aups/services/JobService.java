package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.Job;
import com.example.aups.repositories.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobService {

    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
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
        return jobRepository.save(job);
    }


    public Job update(Long id, Job job) {
        if (jobRepository.findById(id).isEmpty()) {
            throw new CustomException("Job with id " + id + " does not exist.");
        }
        return jobRepository.save(job);
    }

    public void delete(Long id) {
        if (jobRepository.findById(id).isEmpty()) {
            throw new CustomException("Job with id " + id + " does not exist.");
        }
        jobRepository.deleteById(id);
    }
}

