package com.example.aups.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String workingHours;

    @OneToMany(mappedBy = "team")
    private Set<User> technicians;

    @OneToMany(mappedBy = "team")
    private Set<Job> jobs;

    public Team() {}

    public Team(Long id, String name, String description, String workingHours) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workingHours = workingHours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

}
