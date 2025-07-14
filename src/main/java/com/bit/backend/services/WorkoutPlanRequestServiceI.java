package com.bit.backend.services;

import com.bit.backend.dtos.WorkoutPlanRequestDto;
import com.bit.backend.entities.WorkoutPlanRequestEntity;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRequestServiceI {

    WorkoutPlanRequestDto addWorkoutPlanRequestEntity(WorkoutPlanRequestDto workoutPlanRequestDto);
    List<WorkoutPlanRequestDto> getWorkoutPlanRequest();

    WorkoutPlanRequestDto deleteWorkoutPlanRequest(long id);

    WorkoutPlanRequestDto updateStatus(String userId);
}
