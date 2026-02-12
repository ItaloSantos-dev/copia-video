package com.italo.copiavideo.model.report;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "searchs_metrics")
public class SearchMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = (100) )
    private String search;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDate date;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public SearchMetrics() {
    }

    public SearchMetrics(UUID id, String search, Integer quantity, LocalDate date) {
        this.id = id;
        this.search = search;
        this.quantity = quantity;
        this.date = date;
    }

    public SearchMetrics(String search, Integer quantity, LocalDate date) {
        this.search = search;
        this.quantity = quantity;
        this.date = date;
    }
}
