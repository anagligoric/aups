package com.example.aups.controllers;

import com.example.aups.models.Job;
import com.example.aups.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/job")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Job> addJob(@RequestBody Job job){
        jobService.create(job);
        return ResponseEntity.ok(job);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Job> updateJob(@PathVariable("id") Long id, @RequestBody Job job) {
        jobService.update(id, job);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable("id") Long id) {
        jobService.delete(id);
    }
}
