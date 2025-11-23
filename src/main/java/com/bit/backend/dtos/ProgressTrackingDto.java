package com.bit.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ProgressTrackingDto {

    private long id;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private byte[] frontImage;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String frontImageName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String frontImageType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private byte[] sideImage;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sideImageName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sideImageType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private byte[] backImage;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String backImageName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String backImageType;


    // Base64 strings for frontend image display
    private String frontImageBase64;
    private String sideImageBase64;
    private String backImageBase64;

    // Getters and setters
    public String getFrontImageBase64() {
        return frontImageBase64;
    }

    public void setFrontImageBase64(String frontImageBase64) {
        this.frontImageBase64 = frontImageBase64;
    }

    public String getSideImageBase64() {
        return sideImageBase64;
    }

    public void setSideImageBase64(String sideImageBase64) {
        this.sideImageBase64 = sideImageBase64;
    }

    public String getBackImageBase64() {
        return backImageBase64;
    }

    public void setBackImageBase64(String backImageBase64) {
        this.backImageBase64 = backImageBase64;
    }

    public ProgressTrackingDto() {
    }

    public ProgressTrackingDto(long id, LocalDate date, double weight, double height, double waist, double hip, double neck, double bmi, double bodyFat, String gender, String remarks, String userName, byte[] frontImage, String frontImageName, String frontImageType, byte[] sideImage, String sideImageName, String sideImageType, byte[] backImage, String backImageName, String backImageType) {
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
