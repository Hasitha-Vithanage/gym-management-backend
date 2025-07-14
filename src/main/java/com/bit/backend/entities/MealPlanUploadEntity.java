package com.bit.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "mealPlanUpload")
public class MealPlanUploadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meal_plan_title", nullable = false)
    private String mealPlanTitle;

    @Column(name = "plan_description")
    private String planDescription;

    @Column(name = "userId")
    private String userId;

    // Image fields (can be large, use @Lob)
    @Lob
    @Column(name = "pdf")
    private byte[] pdf;

    @Column(name = "pdf_name")
    private String pdfName;

    @Column(name = "pdf_type")
    private String pdfType;

    @Column(name = "upload_date", nullable = false)
    @CreationTimestamp
    private LocalDate uploadDate;

    public MealPlanUploadEntity() {
    }

    public MealPlanUploadEntity(Long id, String mealPlanTitle, String planDescription, String userId, byte[] pdf, String pdfName, String pdfType, LocalDate uploadDate) {
        this.id = id;
        this.mealPlanTitle = mealPlanTitle;
        this.planDescription = planDescription;
        this.userId = userId;
        this.pdf = pdf;
        this.pdfName = pdfName;
        this.pdfType = pdfType;
        this.uploadDate = uploadDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
