package com.bit.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "membershipCategory")
public class MembershipCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "fee")
    private double fee;

    @Column(name = "date", updatable = false)
    @CreationTimestamp
    private LocalDate date;

    public MembershipCategoryEntity() {
    }

    public MembershipCategoryEntity(long id, String categoryName, double fee, LocalDate date) {
        this.id = id;
        this.categoryName = categoryName;
        this.fee = fee;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
