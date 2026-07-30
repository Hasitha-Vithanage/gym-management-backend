package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "progress-tracking")
public class ProgressTrackingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "waist")
    private double waist;

    @Column(name = "hip")
    private double hip;

    @Column(name = "neck")
    private double neck;

    @Column(name = "bmi")
    private double bmi;

    @Column(name = "bodyFat")
    private double bodyFat;

    @Column(name = "gender")
    private String gender;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "userName")
    private String userName;

    public ProgressTrackingEntity() {
    }

    public ProgressTrackingEntity(Long id, LocalDate date, double weight, double height, double waist, double hip, double neck, double bmi, double bodyFat, String gender, String remarks, String userName) {
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
