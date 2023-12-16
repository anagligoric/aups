package com.example.aups.models;

import com.example.aups.enums.JobStatus;

import javax.persistence.*;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String description;
    private JobStatus status;

    @ManyToOne
    @JoinColumn(name="client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    public Job() {}

    public Job(Long id, String type, String description, Client client) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.client = client;
    }

    public Job(String type, String description, JobStatus status, Client client, User user) {
        this.type = type;
        this.description = description;
        this.status = status;
        this.client = client;
        this.user = user;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
