package com.example.aups.models;

import javax.persistence.*;

@Entity
public class Lokacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grad;

    private String ulica;

    private Integer broj;

    @ManyToOne
    @JoinColumn(name="korisnik_id", referencedColumnName = "id")
    private Korisnik korisnik;

    public Lokacija() {
    }

    public Lokacija(Long id, String grad, String ulica, Integer broj, Korisnik korisnik) {
        this.id = id;
        this.grad = grad;
        this.ulica = ulica;
        this.broj = broj;
        this.korisnik = korisnik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
