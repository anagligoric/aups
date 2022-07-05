package com.example.aups.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Tim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String opis;
    private String radnoVreme;

    @OneToMany(mappedBy = "tim")
    private Set<User> tehnicari;

    @OneToMany(mappedBy = "tim")
    private Set<Posao> poslovi;

    public Tim() {}

    public Tim(Long id, String naziv, String opis, String radnoVreme) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.radnoVreme = radnoVreme;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }

    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

}
