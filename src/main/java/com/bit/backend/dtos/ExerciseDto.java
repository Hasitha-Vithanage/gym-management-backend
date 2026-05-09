package com.bit.backend.dtos;

public class ExerciseDto {

    private Long id;
    private String exerciseName;
    private String description;
    private String instructions;
    private String muscleGroup;
    private String muscleGroupSecondary;
    private String exerciseType;
    private String movementType;
    private String difficultyLevel;
    private String intensityLevel;
    private String equipmentType;
    private String location;
    private String goalType;
    private String suitableFor;
    private Boolean isDeleted = Boolean.FALSE;

    public ExerciseDto() {
    }

    public ExerciseDto(Long id, String exerciseName, String description, String instructions, String muscleGroup, String muscleGroupSecondary, String exerciseType, String movementType, String difficultyLevel, String intensityLevel, String equipmentType, String location, String goalType, String suitableFor, Boolean isDeleted) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.description = description;
        this.instructions = instructions;
        this.muscleGroup = muscleGroup;
        this.muscleGroupSecondary = muscleGroupSecondary;
        this.exerciseType = exerciseType;
        this.movementType = movementType;
        this.difficultyLevel = difficultyLevel;
        this.intensityLevel = intensityLevel;
        this.equipmentType = equipmentType;
        this.location = location;
        this.goalType = goalType;
        this.suitableFor = suitableFor;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getMuscleGroupSecondary() {
        return muscleGroupSecondary;
    }

    public void setMuscleGroupSecondary(String muscleGroupSecondary) {
        this.muscleGroupSecondary = muscleGroupSecondary;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(String intensityLevel) {
        this.intensityLevel = intensityLevel;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public String getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(String suitableFor) {
        this.suitableFor = suitableFor;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
