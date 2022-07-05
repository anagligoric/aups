package com.example.aups.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Alat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;

    @OneToMany(mappedBy = "alat")
    private Set<AlatZaNalog> alatiZaNalog;

    public Alat() {

    }

    public Alat(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
