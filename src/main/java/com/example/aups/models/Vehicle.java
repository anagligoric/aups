package com.example.aups.models;

import javax.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String licencePlate;

    private String type;

    @ManyToOne
    @JoinColumn(name="document_id", referencedColumnName = "id")
    private Document document;

    public Vehicle() {

    }

    public Vehicle(Long id, String name, String licencePlate, String type, Document document) {
        this.id = id;
        this.name = name;
        this.licencePlate = licencePlate;
        this.type = type;
        this.document = document;
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

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Document getNalog() {
        return document;
    }

    public void setNalog(Document document) {
        this.document = document;
    }
}
