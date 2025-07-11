package com.bit.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table (name = "WorkoutPlanRequest")
public class WorkoutPlanRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "age")
    private int age;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "fitnessGoal")
    private String fitnessGoal;

    @Column(name = "experienceLevel")
    private String experienceLevel;

    @Column(name = "trainerId")
    private long trainerId;

    @Column(name = "status")
    private String status = "requested";

    @Column(name = "date", updatable = false)
    @CreationTimestamp
    private LocalDate date;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = "requested";
        }
    }

    public WorkoutPlanRequestEntity() {
    }

    public WorkoutPlanRequestEntity(long id, String userId, int age, double weight, double height, String fitnessGoal, String experienceLevel, long trainerId, String status, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.fitnessGoal = fitnessGoal;
        this.experienceLevel = experienceLevel;
        this.trainerId = trainerId;
        this.status = status;
        this.date = date;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
