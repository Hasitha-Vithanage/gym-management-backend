package com.bit.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkoutPlanDto {

    private long id;
    private String workoutPlanTitle;
    private String planDescription;
    private String userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private byte[] pdf;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String pdfName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String pdfType;

    public WorkoutPlanDto() {
    }

    public WorkoutPlanDto(long id, String workoutPlanTitle, String planDescription, String userId, byte[] pdf, String pdfName, String pdfType) {
        this.id = id;
        this.workoutPlanTitle = workoutPlanTitle;
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

    public String getWorkoutPlanTitle() {
        return workoutPlanTitle;
    }

    public void setWorkoutPlanTitle(String workoutPlanTitle) {
        this.workoutPlanTitle = workoutPlanTitle;
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
