package com.bit.backend.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutTemplateDto {

    private Long id;
    private String templateName;
    private String description;

    // Classification
    private String goal;
    private String difficultyLevel;
    private String intensityLevel;
    private String location;

    // Schedule
    private Integer durationMinutes;
    private Integer daysPerWeek;
    private Integer programLengthWeeks;

    // Equipment & Targeting
    private List<String> equipmentRequired;
    private String suitableFor;
    private List<String> recommendedBMI;

    // Meta
    private String status;
    private Boolean isDeleted = Boolean.FALSE;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public WorkoutTemplateDto() {
    }

    public WorkoutTemplateDto(Long id, String templateName, String description, String goal, String difficultyLevel, String intensityLevel, String location, Integer durationMinutes, Integer daysPerWeek, Integer programLengthWeeks, List<String> equipmentRequired, String suitableFor, List<String> recommendedBMI, String status, Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
