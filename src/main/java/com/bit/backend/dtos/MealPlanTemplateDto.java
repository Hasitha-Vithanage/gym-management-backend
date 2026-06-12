package com.bit.backend.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class MealPlanTemplateDto {

    private Long id;
    private String templateName;
    private String description;
    private String goal;

    // Daily macro targets
    private Integer dailyCalorieTarget;
    private Integer proteinTargetG;
    private Integer carbsTargetG;
    private Integer fatTargetG;
    private Integer durationWeeks;

    // Matching criteria
    private List<String> suitableGoals;
    private List<String> suitableBmiCategories;
    private List<String> dietaryTags;

    // Derived — populated by service layer
    private Integer foodItemCount;

    // Meta
    private String status;
    private Boolean isDeleted = Boolean.FALSE;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MealPlanTemplateDto() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }

    public Integer getDailyCalorieTarget() { return dailyCalorieTarget; }
    public void setDailyCalorieTarget(Integer dailyCalorieTarget) { this.dailyCalorieTarget = dailyCalorieTarget; }

    public Integer getProteinTargetG() { return proteinTargetG; }
    public void setProteinTargetG(Integer proteinTargetG) { this.proteinTargetG = proteinTargetG; }

    public Integer getCarbsTargetG() { return carbsTargetG; }
    public void setCarbsTargetG(Integer carbsTargetG) { this.carbsTargetG = carbsTargetG; }

    public Integer getFatTargetG() { return fatTargetG; }
    public void setFatTargetG(Integer fatTargetG) { this.fatTargetG = fatTargetG; }

    public Integer getDurationWeeks() { return durationWeeks; }
    public void setDurationWeeks(Integer durationWeeks) { this.durationWeeks = durationWeeks; }

    public List<String> getSuitableGoals() { return suitableGoals; }
    public void setSuitableGoals(List<String> suitableGoals) { this.suitableGoals = suitableGoals; }

    public List<String> getSuitableBmiCategories() { return suitableBmiCategories; }
    public void setSuitableBmiCategories(List<String> suitableBmiCategories) { this.suitableBmiCategories = suitableBmiCategories; }

    public List<String> getDietaryTags() { return dietaryTags; }
    public void setDietaryTags(List<String> dietaryTags) { this.dietaryTags = dietaryTags; }

    public Integer getFoodItemCount() { return foodItemCount; }
    public void setFoodItemCount(Integer foodItemCount) { this.foodItemCount = foodItemCount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getDeleted() { return isDeleted; }
    public void setDeleted(Boolean deleted) { isDeleted = deleted; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
