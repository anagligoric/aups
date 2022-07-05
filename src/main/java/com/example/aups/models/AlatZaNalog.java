package com.example.aups.models;

import javax.persistence.*;

@Entity
public class AlatZaNalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer kolicina;


    @ManyToOne
    @JoinColumn(name="alat_id", referencedColumnName = "id")
    private Alat alat;

    @ManyToOne
    @JoinColumn(name="nalog_id", referencedColumnName = "id")
    private Nalog nalog;

    public AlatZaNalog() {}

    public AlatZaNalog(Long id, Integer kolicina) {
        this.id = id;
        this.kolicina = kolicina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }
}
