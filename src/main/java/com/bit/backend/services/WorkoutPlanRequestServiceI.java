package com.bit.backend.services;

import com.bit.backend.dtos.WorkoutPlanRequestDto;
import com.bit.backend.entities.WorkoutPlanRequestEntity;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRequestServiceI {

    WorkoutPlanRequestDto addWorkoutPlanRequestEntity(WorkoutPlanRequestDto workoutPlanRequestDto);
    List<WorkoutPlanRequestDto> getWorkoutPlanRequest();
    List<WorkoutPlanRequestDto> getRequestsByStatus(String status);
    List<WorkoutPlanRequestDto> getPendingCustomRequestsForTrainer(Long trainerUserId);
    WorkoutPlanRequestDto getLastRequestByUserId(String userId);
    WorkoutPlanRequestDto updateStatus(long id);
    WorkoutPlanRequestDto updateStatusByUserId(String userId, String status);
    WorkoutPlanRequestDto deleteWorkoutPlanRequest(long id);
}
