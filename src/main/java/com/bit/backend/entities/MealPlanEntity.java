package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "nutrition_and_meal_plans")
public class MealPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "requestedDate")
    private LocalDate requestedDate;

    @Column(name = "fitnessGoal")
    private String fitnessGoal;

    @ElementCollection
    @CollectionTable(name = "meal_plan_dietaryPreferences", joinColumns = @JoinColumn(name = "equipment_id"))
    @Column(name = "dietaryPreferences")
    private List<String> dietaryPreferences;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "status")
    private String status;

    @Column(name = "additionalNotes")
    private String additionalNotes;

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = "Requested";
        }
    }

    public MealPlanEntity() {
    }

    public MealPlanEntity(long id, String userId, LocalDate requestedDate, String fitnessGoal, List<String> dietaryPreferences, String allergies, String status, String additionalNotes) {
        this.id = id;
        this.userId = userId;
        this.requestedDate = requestedDate;
        this.fitnessGoal = fitnessGoal;
        this.dietaryPreferences = dietaryPreferences;
        this.allergies = allergies;
        this.status = status;
        this.additionalNotes = additionalNotes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public List<String> getDietaryPreferences() {
        return dietaryPreferences;
    }

    public void setDietaryPreferences(List<String> dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}
