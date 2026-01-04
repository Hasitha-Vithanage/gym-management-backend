package com.bit.backend.services;

import com.bit.backend.dtos.WorkoutPlanGenerateDto;
import reactor.core.publisher.Flux;

public interface WorkoutPlanGenerateServiceI {
    Flux<String> generateWorkoutPlan(WorkoutPlanGenerateDto request);
}
