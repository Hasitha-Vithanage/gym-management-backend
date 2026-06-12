package com.bit.backend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "food_item")
public class FoodItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_name", nullable = false, length = 150)
    private String foodName;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "calories_per_100g", nullable = false)
    private Double caloriesPer100g;

    @Column(name = "protein_g", nullable = false)
    private Double proteinG;

    @Column(name = "carbs_g", nullable = false)
    private Double carbsG;

    @Column(name = "fat_g", nullable = false)
    private Double fatG;

    @Column(name = "serving_description", length = 200)
    private String servingDescription;

    @ElementCollection
    @CollectionTable(name = "food_item_dietary_tags", joinColumns = @JoinColumn(name = "food_item_id"))
    @Column(name = "tag")
    private List<String> dietaryTags;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    public FoodItemEntity() {
    }

    public FoodItemEntity(Long id, String foodName, String category, Double caloriesPer100g,
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
