package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.JobPlan;
import com.example.aups.repositories.JobPlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobPlanService {

    private JobPlanRepository jobPlanRepository;

    public JobPlanService(JobPlanRepository jobPlanRepository) {
        this.jobPlanRepository = jobPlanRepository;
    }

    @Transactional(readOnly = true)
    public List<JobPlan> getAllJobPlans() {
        return jobPlanRepository.findAll();
    }

    @Transactional(readOnly = true)
    public JobPlan getJobPlanById(Long id) {
        return jobPlanRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Job plan with id " + id + " does not exist."));
    }

    public JobPlan create(JobPlan jobPlan) {
        return jobPlanRepository.save(jobPlan);
    }


    public JobPlan update(Long id, JobPlan jobPlan) {
        if (jobPlanRepository.findById(id).isEmpty()) {
            throw new CustomException("Job plan with id " + id + " does not exist.");
        }
        return jobPlanRepository.save(jobPlan);
    }

    public void delete(Long id) {
        if (jobPlanRepository.findById(id).isEmpty()) {
            throw new CustomException("Job plan with id " + id + " does not exist.");
        }
        jobPlanRepository.deleteById(id);
    }
}

