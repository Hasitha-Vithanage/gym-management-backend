package com.bit.backend.dtos;

import java.util.List;

public class TemplateMealItemDto {

    private Long id;
    private Long templateId;
    private Long foodItemId;
    private String foodItemName;
    private Integer dayOfWeek;
    private String mealSlot;
    private Double portionGrams;
    private Double caloriesForPortion;
    private Integer mealOrder;
    private String notes;

    // Enriched from FoodItemEntity by the service layer
    private String category;
    private List<String> dietaryTags;

    public TemplateMealItemDto() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTemplateId() { return templateId; }
    public void setTemplateId(Long templateId) { this.templateId = templateId; }

    public Long getFoodItemId() { return foodItemId; }
    public void setFoodItemId(Long foodItemId) { this.foodItemId = foodItemId; }

    public String getFoodItemName() { return foodItemName; }
    public void setFoodItemName(String foodItemName) { this.foodItemName = foodItemName; }

    public Integer getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(Integer dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getMealSlot() { return mealSlot; }
    public void setMealSlot(String mealSlot) { this.mealSlot = mealSlot; }

    public Double getPortionGrams() { return portionGrams; }
    public void setPortionGrams(Double portionGrams) { this.portionGrams = portionGrams; }

    public Double getCaloriesForPortion() { return caloriesForPortion; }
    public void setCaloriesForPortion(Double caloriesForPortion) { this.caloriesForPortion = caloriesForPortion; }

    public Integer getMealOrder() { return mealOrder; }
    public void setMealOrder(Integer mealOrder) { this.mealOrder = mealOrder; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public List<String> getDietaryTags() { return dietaryTags; }
    public void setDietaryTags(List<String> dietaryTags) { this.dietaryTags = dietaryTags; }
}
