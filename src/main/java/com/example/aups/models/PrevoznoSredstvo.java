package com.example.aups.models;

import javax.persistence.*;

@Entity
public class PrevoznoSredstvo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private String registarskaOznaka;

    private String tip;

    @ManyToOne
    @JoinColumn(name="nalog_id", referencedColumnName = "id")
    private Nalog nalog;

    public PrevoznoSredstvo() {

    }

    public PrevoznoSredstvo(Long id, String naziv, String registarskaOznaka, String tip, Nalog nalog) {
        this.id = id;
        this.naziv = naziv;
        this.registarskaOznaka = registarskaOznaka;
        this.tip = tip;
        this.nalog = nalog;
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

    public String getRegistarskaOznaka() {
        return registarskaOznaka;
    }

    public void setRegistarskaOznaka(String registarskaOznaka) {
        this.registarskaOznaka = registarskaOznaka;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
    }
}
