package com.bit.backend.services;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.WorkoutPlanDto;
import com.bit.backend.dtos.WorkoutPlanRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface WorkoutPlanServiceI {

    WorkoutPlanDto addWorkoutPlanEntity(WorkoutPlanDto workoutPlanDto);
    WorkoutPlanDto getWorkoutPlanByUserId(String userId);
}
