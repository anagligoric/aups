package com.example.aups.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private Date creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Long price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_plan_id", referencedColumnName = "id")
    private JobPlan jobPlan;

    @OneToMany(mappedBy = "document")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "document")
    private Set<DocumentSparePart> documentSpareParts;

    @OneToMany(mappedBy = "document")
    private Set<DocumentTool> documentTools;

    public Document() {}

    public Document(Long id, String number, Date creationDate, Long price) {
        this.id = id;
        this.number = number;
        this.creationDate = creationDate;
        this.price = price;
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

    public Job getPosao() {
        return job;
    }

    public void setPosao(Job job) {
        this.job = job;
    }
}
