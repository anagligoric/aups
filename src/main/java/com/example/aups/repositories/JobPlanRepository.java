package com.example.aups.repositories;

import com.example.aups.models.JobPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPlanRepository extends JpaRepository<JobPlan, Long> {
}
