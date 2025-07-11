package com.bit.backend.services;

import com.bit.backend.dtos.WorkoutPlanRequestDto;

import java.util.List;

public interface WorkoutPlanRequestServiceI {

    WorkoutPlanRequestDto addWorkoutPlanRequestEntity(WorkoutPlanRequestDto workoutPlanRequestDto);
    List<WorkoutPlanRequestDto> getWorkoutPlanRequest();

}
