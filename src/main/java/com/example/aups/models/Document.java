package com.example.aups.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private Date creationDate;
    private Long price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @ManyToOne
    @JoinColumn(name="vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "document_tools",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "tool_id")
    )
    private Set<Tool> tools = new HashSet<>();

    public Document() {}

    public Document(Long id, String number, Date creationDate, Long price) {
        this.id = id;
        this.number = number;
        this.creationDate = creationDate;
        this.price = price;
    }

    public Document(Long id, String number, Date creationDate, Long price, Job job, Vehicle vehicle) {
        this.id = id;
        this.number = number;
        this.creationDate = creationDate;
        this.price = price;
        this.job = job;
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Set<Tool> getTools() {
        return tools;
    }

    public void setTools(Set<Tool> tools) {
        this.tools = tools;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
