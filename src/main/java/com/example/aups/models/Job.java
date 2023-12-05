package com.example.aups.models;

import javax.persistence.*;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String description;

    @ManyToOne
    @JoinColumn(name="client_id", referencedColumnName = "id")
    private Client client;


    @OneToOne(mappedBy = "job")
    private Document document;

    public Job() {}

    public Job(Long id, String type, String description, Client client) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.client = client;
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

    public Client getKorisnik() {
        return client;
    }

    public void setKorisnik(Client client) {
        this.client = client;
    }

    public Document getNalog() {
        return document;
    }

    public void setNalog(Document document) {
        this.document = document;
    }
}
