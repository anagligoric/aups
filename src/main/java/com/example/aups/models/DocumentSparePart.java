package com.example.aups.models;

import javax.persistence.*;

@Entity
public class DocumentSparePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;

    @ManyToOne
    @JoinColumn(name="spare_part_id", referencedColumnName = "id")
    private SparePart sparePart;

    @ManyToOne
    @JoinColumn(name="document_id", referencedColumnName = "id")
    private Document document;

    public DocumentSparePart() {
    }

    public DocumentSparePart(Long id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
