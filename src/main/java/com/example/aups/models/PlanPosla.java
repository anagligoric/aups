package com.example.aups.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PlanPosla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date vremePocetka;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date vremeZavrsetka;

    @OneToOne(mappedBy = "planPosla")
    private Nalog nalog;

    public PlanPosla() {
    }

    public PlanPosla(Long id, Date vremePocetka, Date vremeZavrsetka) {
        this.id = id;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(Date vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public Date getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(Date vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }
}
