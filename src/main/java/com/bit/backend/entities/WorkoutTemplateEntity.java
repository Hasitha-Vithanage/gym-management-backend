package com.bit.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workout_template")
public class WorkoutTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_name", nullable = false, length = 100)
    private String templateName;

    @Column(name = "description", length = 2000)
    private String description;

    // Classification
    @Column(name = "goal", nullable = false)
    private String goal;

    @Column(name = "difficulty_level", nullable = false)
    private String difficultyLevel;

    @Column(name = "intensity_level", nullable = false)
    private String intensityLevel;

    @Column(name = "location", nullable = false)
    private String location;

    // Schedule
    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(name = "days_per_week", nullable = false)
    private Integer daysPerWeek;

    @Column(name = "program_length_weeks")
    private Integer programLengthWeeks;

    // Equipment — stored as separate table
    @ElementCollection
    @CollectionTable(
            name = "template_equipment",
            joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "equipment")
    private List<String> equipmentRequired;

    // Targeting
    @Column(name = "suitable_for", nullable = false)
    private String suitableFor;

    // BMI — stored as separate table
    @ElementCollection
    @CollectionTable(
            name = "template_bmi",
            joinColumns = @JoinColumn(name = "template_id")
    )
    @Column(name = "bmi_category")
    private List<String> recommendedBMI;

    // Meta
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public WorkoutTemplateEntity() {
    }

    public WorkoutTemplateEntity(Long id, String templateName, String description, String goal, String difficultyLevel, String intensityLevel, String location, Integer durationMinutes, Integer daysPerWeek, Integer programLengthWeeks, List<String> equipmentRequired, String suitableFor, List<String> recommendedBMI, String status, Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.templateName = templateName;
        this.description = description;
        this.goal = goal;
        this.difficultyLevel = difficultyLevel;
        this.intensityLevel = intensityLevel;
        this.location = location;
        this.durationMinutes = durationMinutes;
        this.daysPerWeek = daysPerWeek;
        this.programLengthWeeks = programLengthWeeks;
        this.equipmentRequired = equipmentRequired;
        this.suitableFor = suitableFor;
        this.recommendedBMI = recommendedBMI;
        this.status = status;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(String intensityLevel) {
        this.intensityLevel = intensityLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Integer getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(Integer daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public Integer getProgramLengthWeeks() {
        return programLengthWeeks;
    }

    public void setProgramLengthWeeks(Integer programLengthWeeks) {
        this.programLengthWeeks = programLengthWeeks;
    }

    public List<String> getEquipmentRequired() {
        return equipmentRequired;
    }

    public void setEquipmentRequired(List<String> equipmentRequired) {
        this.equipmentRequired = equipmentRequired;
    }

    public String getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(String suitableFor) {
        this.suitableFor = suitableFor;
    }

    public List<String> getRecommendedBMI() {
        return recommendedBMI;
    }

    public void setRecommendedBMI(List<String> recommendedBMI) {
        this.recommendedBMI = recommendedBMI;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
