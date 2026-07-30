package com.bit.backend.dtos;

import java.time.LocalDate;

public class ProgressTrackingDto {

    private Long id;
    private LocalDate date;
    private double weight;
    private double height;
    private double waist;
    private double hip;
    private double neck;
    private double bmi;
    private double bodyFat;
    private String gender;
    private String remarks;
    private String userName;

    public ProgressTrackingDto() {
    }

    public ProgressTrackingDto(Long id, LocalDate date, double weight, double height, double waist, double hip, double neck, double bmi, double bodyFat, String gender, String remarks, String userName) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.waist = waist;
        this.hip = hip;
        this.neck = neck;
        this.bmi = bmi;
        this.bodyFat = bodyFat;
        this.gender = gender;
        this.remarks = remarks;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHip() {
        return hip;
    }

    public void setHip(double hip) {
        this.hip = hip;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(double bodyFat) {
        this.bodyFat = bodyFat;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
