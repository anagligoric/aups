package com.example.aups.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;
    private String prezime;
    private String brojTelefona;

    @OneToMany(mappedBy = "korisnik")
    private Set<Lokacija> lokacije;


    @OneToMany(mappedBy = "korisnik")
    private Set<Posao> poslovi;

    public Korisnik () {}

    public Korisnik(Long id, String ime, String prezime, String brojTelefona) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Set<Lokacija> getLokacije() {
        return lokacije;
    }

    public void setLokacije(Set<Lokacija> lokacije) {
        this.lokacije = lokacije;
    }

    public Set<Posao> getPoslovi() {
        return poslovi;
    }

    public void setPoslovi(Set<Posao> poslovi) {
        this.poslovi = poslovi;
    }
}
