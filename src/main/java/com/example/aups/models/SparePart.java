package com.example.aups.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SparePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "sparePart")
    private Set<DocumentSparePart> documentSpareParts;

    public SparePart() {
    }

    public SparePart(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
