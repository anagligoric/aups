package com.example.aups.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Nalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String broj;
    private Date datumKreiranja;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Long cena;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posao_id", referencedColumnName = "id")
    private Posao posao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_posla_id", referencedColumnName = "id")
    private PlanPosla planPosla;

    @OneToMany(mappedBy = "nalog")
    private Set<PrevoznoSredstvo> prevoznaSredstva;

    @OneToMany(mappedBy = "nalog")
    private Set<RezervniDeoZaNalog> rezervniDeloviZaNalog;

    @OneToMany(mappedBy = "nalog")
    private Set<AlatZaNalog> alatiZaNalog;

    public Nalog() {}

    public Nalog(Long id, String broj, Date datumKreiranja, Long cena) {
        this.id = id;
        this.broj = broj;
        this.datumKreiranja = datumKreiranja;
        this.cena = cena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Long getCena() {
        return cena;
    }

    public void setCena(Long cena) {
        this.cena = cena;
    }

    public Posao getPosao() {
        return posao;
    }

    public void setPosao(Posao posao) {
        this.posao = posao;
    }
}
