package com.example.aups.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Posao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tip;
    private String opis;

    @ManyToOne
    @JoinColumn(name="tim_id", referencedColumnName = "id")
    private Tim tim;

    @ManyToOne
    @JoinColumn(name="korisnik_id", referencedColumnName = "id")
    private Korisnik korisnik;


    @OneToOne(mappedBy = "posao")
    private Nalog nalog;

    public Posao() {}

    public Posao(Long id, String tip, String opis, Tim tim, Korisnik korisnik) {
        this.id = id;
        this.tip = tip;
        this.opis = opis;
        this.tim = tim;
        this.korisnik = korisnik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
    }
}
