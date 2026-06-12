package com.bit.backend.dtos;

import java.util.List;

public class FoodItemDto {

    private Long id;
    private String foodName;
    private String category;
    private Double caloriesPer100g;
    private Double proteinG;
    private Double carbsG;
    private Double fatG;
    private String servingDescription;
    private List<String> dietaryTags;
    private Boolean isDeleted = Boolean.FALSE;

    public FoodItemDto() {
    }

    public FoodItemDto(Long id, String foodName, String category, Double caloriesPer100g,
                       Double proteinG, Double carbsG, Double fatG,
                       String servingDescription, List<String> dietaryTags, Boolean isDeleted) {
        this.id = id;
        this.foodName = foodName;
        this.category = category;
        this.caloriesPer100g = caloriesPer100g;
        this.proteinG = proteinG;
        this.carbsG = carbsG;
        this.fatG = fatG;
        this.servingDescription = servingDescription;
        this.dietaryTags = dietaryTags;
        this.isDeleted = isDeleted;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getCaloriesPer100g() { return caloriesPer100g; }
    public void setCaloriesPer100g(Double caloriesPer100g) { this.caloriesPer100g = caloriesPer100g; }

    public Double getProteinG() { return proteinG; }
    public void setProteinG(Double proteinG) { this.proteinG = proteinG; }

    public Double getCarbsG() { return carbsG; }
    public void setCarbsG(Double carbsG) { this.carbsG = carbsG; }

    public Double getFatG() { return fatG; }
    public void setFatG(Double fatG) { this.fatG = fatG; }

    public String getServingDescription() { return servingDescription; }
    public void setServingDescription(String servingDescription) { this.servingDescription = servingDescription; }

    public List<String> getDietaryTags() { return dietaryTags; }
    public void setDietaryTags(List<String> dietaryTags) { this.dietaryTags = dietaryTags; }

    public Boolean getDeleted() { return isDeleted; }
    public void setDeleted(Boolean deleted) { isDeleted = deleted; }
}
