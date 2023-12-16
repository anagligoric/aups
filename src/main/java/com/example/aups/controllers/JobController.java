package com.example.aups.controllers;

import com.example.aups.enums.JobStatus;
import com.example.aups.models.Job;
import com.example.aups.models.JobDto;
import com.example.aups.security.CurrentSession;
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
    private final CurrentSession currentSession;

    public JobController(JobService jobService, CurrentSession currentSession) {
        this.jobService = jobService;
        this.currentSession = currentSession;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/my/{email}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    public ResponseEntity<List<Job>> getMyJobs(@PathVariable String email) {
        return ResponseEntity.ok(jobService.getMyJobs(email));
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JobDto> addJob(@RequestBody JobDto jobDto){
        jobService.create(jobDto);
        return ResponseEntity.ok(jobDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JobDto> updateJob(@PathVariable("id") Long id, @RequestBody JobDto job) {
        jobService.update(id, job);
        return ResponseEntity.ok(job);
    }


    @PutMapping("/update-status/{id}")
    @PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")
    public ResponseEntity<Job> updateJobStatus(@PathVariable("id") Long id, @RequestParam JobStatus jobStatus) {
        Job job = jobService.updateStatus(id, jobStatus);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable("id") Long id) {
        jobService.delete(id);
    }
}
