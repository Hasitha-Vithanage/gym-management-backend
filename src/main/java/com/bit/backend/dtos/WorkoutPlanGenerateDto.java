package com.bit.backend.dtos;

public class WorkoutPlanGenerateDto {
    private int age;
    private String gender;
    private String experience;
    private String goal;
    private String equipment;
    private int daysPerWeek;
    private String limitation;
    private float height;
    private float weight;
    private String location;

    public WorkoutPlanGenerateDto() {
    }

    public WorkoutPlanGenerateDto(int age, String gender, String experience, String goal, String equipment, int daysPerWeek, String limitation, float height, float weight, String location) {
        this.age = age;
        this.gender = gender;
        this.experience = experience;
        this.goal = goal;
        this.equipment = equipment;
        this.daysPerWeek = daysPerWeek;
        this.limitation = limitation;
        this.height = height;
        this.weight = weight;
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public int getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(int daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public String getLimitation() {
        return limitation;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
