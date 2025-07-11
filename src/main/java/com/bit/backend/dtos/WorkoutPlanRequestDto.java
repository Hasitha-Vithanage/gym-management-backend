package com.bit.backend.dtos;

public class WorkoutPlanRequestDto {

    private long id;
    private String userId;
    private int age;
    private double weight;
    private double height;
    private String fitnessGoal;
    private String experienceLevel;
    private long trainerId;

    public WorkoutPlanRequestDto() {
    }

    public WorkoutPlanRequestDto(long id, String userId, int age, double weight, double height, String fitnessGoal, String experienceLevel, long trainerId) {
        this.id = id;
        this.userId = userId;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.fitnessGoal = fitnessGoal;
        this.experienceLevel = experienceLevel;
        this.trainerId = trainerId;
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
}
