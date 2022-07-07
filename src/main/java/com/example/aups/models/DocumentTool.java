package com.example.aups.models;

import javax.persistence.*;

@Entity
public class DocumentTool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;


    @ManyToOne
    @JoinColumn(name="tool_id", referencedColumnName = "id")
    private Tool tool;

    @ManyToOne
    @JoinColumn(name="document_id", referencedColumnName = "id")
    private Document document;

    public DocumentTool() {}

    public DocumentTool(Long id, Integer amount) {
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
