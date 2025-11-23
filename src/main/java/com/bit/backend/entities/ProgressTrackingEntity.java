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

    // frontImage fields (can be large, use @Lob)
    @Lob
    @Column(name = "frontImage")
    private byte[] frontImage;

    @Column(name = "frontImageName")
    private String frontImageName;

    @Column(name = "frontImageType")
    private String frontImageType;

    // sideImage fields
    @Lob
    @Column(name = "sideImage")
    private byte[] sideImage;

    @Column(name = "sideImageName")
    private String sideImageName;

    @Column(name = "sideImageType")
    private String sideImageType;

    // backImage fields
    @Lob
    @Column(name = "backImage")
    private byte[] backImage;

    @Column(name = "backImageName")
    private String backImageName;

    @Column(name = "backImageType")
    private String backImageType;

    public ProgressTrackingEntity() {
    }

    public ProgressTrackingEntity(Long id, LocalDate date, double weight, double height, double waist, double hip, double neck, double bmi, double bodyFat, String gender, String remarks, String userName, byte[] frontImage, String frontImageName, String frontImageType, byte[] sideImage, String sideImageName, String sideImageType, byte[] backImage, String backImageName, String backImageType) {
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
        this.frontImage = frontImage;
        this.frontImageName = frontImageName;
        this.frontImageType = frontImageType;
        this.sideImage = sideImage;
        this.sideImageName = sideImageName;
        this.sideImageType = sideImageType;
        this.backImage = backImage;
        this.backImageName = backImageName;
        this.backImageType = backImageType;
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

    public byte[] getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(byte[] frontImage) {
        this.frontImage = frontImage;
    }

    public String getFrontImageName() {
        return frontImageName;
    }

    public void setFrontImageName(String frontImageName) {
        this.frontImageName = frontImageName;
    }

    public String getFrontImageType() {
        return frontImageType;
    }

    public void setFrontImageType(String frontImageType) {
        this.frontImageType = frontImageType;
    }

    public byte[] getSideImage() {
        return sideImage;
    }

    public void setSideImage(byte[] sideImage) {
        this.sideImage = sideImage;
    }

    public String getSideImageName() {
        return sideImageName;
    }

    public void setSideImageName(String sideImageName) {
        this.sideImageName = sideImageName;
    }

    public String getSideImageType() {
        return sideImageType;
    }

    public void setSideImageType(String sideImageType) {
        this.sideImageType = sideImageType;
    }

    public byte[] getBackImage() {
        return backImage;
    }

    public void setBackImage(byte[] backImage) {
        this.backImage = backImage;
    }

    public String getBackImageName() {
        return backImageName;
    }

    public void setBackImageName(String backImageName) {
        this.backImageName = backImageName;
    }

    public String getBackImageType() {
        return backImageType;
    }

    public void setBackImageType(String backImageType) {
        this.backImageType = backImageType;
    }
}
