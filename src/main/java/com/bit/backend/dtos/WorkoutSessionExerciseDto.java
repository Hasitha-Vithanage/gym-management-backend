package com.bit.backend.dtos;

import java.time.LocalDateTime;

public class WorkoutSessionExerciseDto {

    private Long id;
    private Long sessionId;
    private Long templateExerciseId;
    private Long exerciseId;
    private String exerciseName;
    private Integer setsCompleted;
    private String repsLogged;
    private Double weightKg;
    private Boolean completed;
    private LocalDateTime createdAt;

    public WorkoutSessionExerciseDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSessionId() { return sessionId; }
    public void setSessionId(Long sessionId) { this.sessionId = sessionId; }

    public Long getTemplateExerciseId() { return templateExerciseId; }
    public void setTemplateExerciseId(Long templateExerciseId) { this.templateExerciseId = templateExerciseId; }

    public Long getExerciseId() { return exerciseId; }
    public void setExerciseId(Long exerciseId) { this.exerciseId = exerciseId; }

    public String getExerciseName() { return exerciseName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public Integer getSetsCompleted() { return setsCompleted; }
    public void setSetsCompleted(Integer setsCompleted) { this.setsCompleted = setsCompleted; }

    public String getRepsLogged() { return repsLogged; }
    public void setRepsLogged(String repsLogged) { this.repsLogged = repsLogged; }

    public Double getWeightKg() { return weightKg; }
    public void setWeightKg(Double weightKg) { this.weightKg = weightKg; }

    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
