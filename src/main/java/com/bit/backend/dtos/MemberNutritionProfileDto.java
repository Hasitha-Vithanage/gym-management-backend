package com.bit.backend.dtos;

import java.time.LocalDate;
import java.util.List;

public class MemberNutritionProfileDto {

    private long id;
    private String userId;
    private LocalDate submittedDate;
    private String fitnessGoal;
    private List<String> dietaryPreferences;
    private String allergies;
    private String status;
    private String additionalNotes;

    public MemberNutritionProfileDto() {
    }

    public MemberNutritionProfileDto(long id, String userId, LocalDate submittedDate, String fitnessGoal,
                                     List<String> dietaryPreferences, String allergies,
                                     String status, String additionalNotes) {
        this.id = id;
        this.userId = userId;
        this.submittedDate = submittedDate;
        this.fitnessGoal = fitnessGoal;
        this.dietaryPreferences = dietaryPreferences;
        this.allergies = allergies;
        this.status = status;
        this.additionalNotes = additionalNotes;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public LocalDate getSubmittedDate() { return submittedDate; }
    public void setSubmittedDate(LocalDate submittedDate) { this.submittedDate = submittedDate; }

    public String getFitnessGoal() { return fitnessGoal; }
    public void setFitnessGoal(String fitnessGoal) { this.fitnessGoal = fitnessGoal; }

    public List<String> getDietaryPreferences() { return dietaryPreferences; }
    public void setDietaryPreferences(List<String> dietaryPreferences) { this.dietaryPreferences = dietaryPreferences; }

    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAdditionalNotes() { return additionalNotes; }
    public void setAdditionalNotes(String additionalNotes) { this.additionalNotes = additionalNotes; }
}
