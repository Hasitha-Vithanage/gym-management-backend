package com.bit.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date", updatable = false)
    @CreationTimestamp
    private LocalDate date;


    @Column(name = "totalCost")
    private double totalCost;

    @Column(name = "orderedBy")
    private String orderedBy;

    @Column(name = "status")
    private String status;

    public OrderEntity() {
    }

    public OrderEntity(long id, LocalDate date, double totalCost, String orderedBy, String status) {
        this.id = id;
        this.date = date;
        this.totalCost = totalCost;
        this.orderedBy = orderedBy;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
