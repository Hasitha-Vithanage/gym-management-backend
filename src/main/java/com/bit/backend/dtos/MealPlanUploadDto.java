package com.bit.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MealPlanUploadDto {

    private long id;
    private String mealPlanTitle;
    private String planDescription;
    private String userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private byte[] pdf;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String pdfName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String pdfType;

    public MealPlanUploadDto() {
    }

    public MealPlanUploadDto(long id, String mealPlanTitle, String planDescription, String userId, byte[] pdf, String pdfName, String pdfType) {
        this.id = id;
        this.mealPlanTitle = mealPlanTitle;
        this.planDescription = planDescription;
        this.userId = userId;
        this.pdf = pdf;
        this.pdfName = pdfName;
        this.pdfType = pdfType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMealPlanTitle() {
        return mealPlanTitle;
    }

    public void setMealPlanTitle(String mealPlanTitle) {
        this.mealPlanTitle = mealPlanTitle;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfType() {
        return pdfType;
    }

    public void setPdfType(String pdfType) {
        this.pdfType = pdfType;
    }
}
