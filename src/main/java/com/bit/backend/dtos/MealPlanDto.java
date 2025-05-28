package com.bit.backend.dtos;

import java.time.LocalDate;
import java.util.List;

public class MealPlanDto {
    private long id;
    private String username;
    private LocalDate requestedDate;
    private String fitnessGoal;
    private List<String> dietaryPreferences;
    private String allergies;
    private String additionalNotes;

    public MealPlanDto() {
    }

    public MealPlanDto(long id, String username, LocalDate requestedDate, String fitnessGoal, List<String> dietaryPreferences, String allergies, String additionalNotes) {
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
