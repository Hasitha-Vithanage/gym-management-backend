package com.bit.backend.services;

import com.bit.backend.dtos.TrainerLoginDto;
import com.bit.backend.dtos.WorkoutPlanRequestDto;

public interface WorkoutPlanRequestServiceI {

    WorkoutPlanRequestDto addWorkoutPlanRequestEntity(WorkoutPlanRequestDto workoutPlanRequestDto);

}
