package com.example.aups.models;

import com.example.aups.enums.JobStatus;

public class JobDto {
    private Long id;
    private String type;
    private String description;
    private JobStatus status;
    private Long clientId;
    private Long userId;

    public JobDto() {

    }

    public JobDto(Long id, String type, String description, JobStatus status, Long clientId, Long userId) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.status = status;
        this.clientId = clientId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
