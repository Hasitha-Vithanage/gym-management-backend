package com.bit.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WorkoutSessionDto {

    private Long id;
    private Long assignmentId;
    private Long memberId;
    private Integer workoutDay;
    private LocalDate sessionDate;
    private String status;
    private String notes;
    private LocalDateTime createdAt;
    private List<WorkoutSessionExerciseDto> exercises;

    public WorkoutSessionDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public Integer getWorkoutDay() { return workoutDay; }
    public void setWorkoutDay(Integer workoutDay) { this.workoutDay = workoutDay; }

    public LocalDate getSessionDate() { return sessionDate; }
    public void setSessionDate(LocalDate sessionDate) { this.sessionDate = sessionDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<WorkoutSessionExerciseDto> getExercises() { return exercises; }
    public void setExercises(List<WorkoutSessionExerciseDto> exercises) { this.exercises = exercises; }
}
