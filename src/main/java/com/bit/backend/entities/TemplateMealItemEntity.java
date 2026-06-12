package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "template_meal_item")
public class TemplateMealItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_id", nullable = false)
    private Long templateId;

    @Column(name = "food_item_id", nullable = false)
    private Long foodItemId;

    @Column(name = "food_item_name", nullable = false, length = 150)
    private String foodItemName;

    // 1=Monday ... 7=Sunday
    @Column(name = "day_of_week", nullable = false)
    private Integer dayOfWeek;

    // Breakfast / MidMorning / Lunch / EveningSnack / Dinner
    @Column(name = "meal_slot", nullable = false)
    private String mealSlot;

    @Column(name = "portion_grams")
    private Double portionGrams;

    @Column(name = "calories_for_portion")
    private Double caloriesForPortion;

    @Column(name = "meal_order")
    private Integer mealOrder;

    @Column(name = "notes", length = 500)
    private String notes;

    public TemplateMealItemEntity() {
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
}
