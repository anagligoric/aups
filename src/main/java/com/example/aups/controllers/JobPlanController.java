package com.example.aups.controllers;

import com.example.aups.models.JobPlan;
import com.example.aups.services.JobPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/job/plans")
public class JobPlanController {
    private final JobPlanService jobPlanService;

    public JobPlanController(JobPlanService jobPlanService) {
        this.jobPlanService = jobPlanService;
    }

    @GetMapping
    public ResponseEntity<List<JobPlan>> getAllJobPlans() {
        return ResponseEntity.ok(jobPlanService.getAllJobPlans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPlan> getJobPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(jobPlanService.getJobPlanById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<JobPlan> addJobPlan(@RequestBody JobPlan jobPlan){
        jobPlanService.create(jobPlan);
        return ResponseEntity.ok(jobPlan);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<JobPlan> updateJobPlan(@PathVariable("id") Long id, @RequestBody JobPlan jobPlan) {
        jobPlanService.update(id, jobPlan);
        return ResponseEntity.ok(jobPlan);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJobPlan(@PathVariable("id") Long id) {
        jobPlanService.delete(id);
    }
}
