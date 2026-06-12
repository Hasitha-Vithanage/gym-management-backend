package com.bit.backend.controllers;

import com.bit.backend.dtos.WorkoutPlanDto;
import com.bit.backend.dtos.WorkoutPlanGenerateDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.WorkoutPlanGenerateServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.net.URI;

@RestController
public class WorkoutPlanGenerateController {
    private WorkoutPlanGenerateServiceI workoutPlanGenerateServiceI;

    public WorkoutPlanGenerateController(WorkoutPlanGenerateServiceI workoutPlanGenerateServiceI) {
        this.workoutPlanGenerateServiceI = workoutPlanGenerateServiceI;
    }

    @PostMapping(value = "/workout-plan-generate", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateWorkoutPlan(@RequestBody WorkoutPlanGenerateDto workoutPlanGenerateDto) {
//        Flux<String> workoutPlan = workoutPlanGenerateServiceI.generateWorkoutPlan(workoutPlanGenerateDto);
//        return ResponseEntity.created(URI.create("/workout-plan-generate")).body(workoutPlan);
        return workoutPlanGenerateServiceI.generateWorkoutPlan(workoutPlanGenerateDto)
                .map(token -> token.replace("\r", ""));
    }
}
