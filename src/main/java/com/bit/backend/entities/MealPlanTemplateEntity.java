package com.bit.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "meal_plan_template")
public class MealPlanTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_name", nullable = false, length = 100)
    private String templateName;

    @Column(name = "description", length = 2000)
    private String description;

    // Primary goal this template is designed for
    @Column(name = "goal", nullable = false)
    private String goal;

    // Daily macro targets
    @Column(name = "daily_calorie_target", nullable = false)
    private Integer dailyCalorieTarget;

    @Column(name = "protein_target_g", nullable = false)
    private Integer proteinTargetG;

    @Column(name = "carbs_target_g", nullable = false)
    private Integer carbsTargetG;

    @Column(name = "fat_target_g", nullable = false)
    private Integer fatTargetG;

    @Column(name = "duration_weeks")
    private Integer durationWeeks;

    // Matching criteria for auto-suggest
    @ElementCollection
    @CollectionTable(name = "meal_template_suitable_goals", joinColumns = @JoinColumn(name = "template_id"))
    @Column(name = "goal")
    private List<String> suitableGoals;

    @ElementCollection
    @CollectionTable(name = "meal_template_suitable_bmi", joinColumns = @JoinColumn(name = "template_id"))
    @Column(name = "bmi_category")
    private List<String> suitableBmiCategories;

    @ElementCollection
    @CollectionTable(name = "meal_template_dietary_tags", joinColumns = @JoinColumn(name = "template_id"))
    @Column(name = "tag")
    private List<String> dietaryTags;

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

    public MealPlanTemplateEntity() {
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

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getDeleted() { return isDeleted; }
    public void setDeleted(Boolean deleted) { isDeleted = deleted; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
