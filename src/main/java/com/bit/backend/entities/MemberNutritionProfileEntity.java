package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "member_nutrition_profiles")
public class MemberNutritionProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "submittedDate")
    private LocalDate submittedDate;

    @Column(name = "fitnessGoal")
    private String fitnessGoal;

    @ElementCollection
    @CollectionTable(name = "nutrition_profile_dietary_preferences", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "preference")
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
            this.status = "Active";
        }
    }

    public MemberNutritionProfileEntity() {
    }

    public MemberNutritionProfileEntity(long id, String userId, LocalDate submittedDate, String fitnessGoal,
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
