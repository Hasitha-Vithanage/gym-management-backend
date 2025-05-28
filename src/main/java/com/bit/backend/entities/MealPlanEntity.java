package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "nutrition-and-meal-plans")
public class MealPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

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

    @Column(name = "additionalNotes")
    private String additionalNotes;

    public MealPlanEntity() {
    }

    public MealPlanEntity(long id, String username, LocalDate requestedDate, String fitnessGoal, List<String> dietaryPreferences, String allergies, String additionalNotes) {
        this.id = id;
        this.username = username;
        this.requestedDate = requestedDate;
        this.fitnessGoal = fitnessGoal;
        this.dietaryPreferences = dietaryPreferences;
        this.allergies = allergies;
        this.additionalNotes = additionalNotes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}
