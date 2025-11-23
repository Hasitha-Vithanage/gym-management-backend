package com.bit.backend.dtos;

import java.time.LocalDate;

public class MembershipCategoryDto {

    private long id;
    private String categoryName;
    private double fee;
    private LocalDate date;

    public MembershipCategoryDto() {
    }

    public MembershipCategoryDto(long id, String categoryName, double fee, LocalDate date) {
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
