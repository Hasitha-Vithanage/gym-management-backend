package com.bit.backend.controllers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.WorkoutPlanRequestDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.SupplementInventoryServiceI;
import com.bit.backend.services.WorkoutPlanRequestServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class WorkoutPlanRequestController {

    private WorkoutPlanRequestServiceI workoutPlanRequestServiceI;

    public WorkoutPlanRequestController(WorkoutPlanRequestServiceI workoutPlanRequestServiceI) {
        this.workoutPlanRequestServiceI = workoutPlanRequestServiceI;
    }

    @PostMapping("/sendRequest")
    public ResponseEntity<WorkoutPlanRequestDto> addWorkoutPlanRequest(@RequestBody WorkoutPlanRequestDto workoutPlanRequestDto) {
        try {
            WorkoutPlanRequestDto workoutPlanRequestDtoResponse = workoutPlanRequestServiceI.addWorkoutPlanRequestEntity(workoutPlanRequestDto);
            return ResponseEntity.created(URI.create("/sendRequest" + workoutPlanRequestDtoResponse.getId())).body(workoutPlanRequestDtoResponse);
        } catch (Exception e) {
            throw new AppException("Workout Plan Request failed. Please try again later. " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/workout-plan-upload")
    public ResponseEntity<List<WorkoutPlanRequestDto>> getWorkoutPlanRequest() {
        try {
            List<WorkoutPlanRequestDto> workoutPlanRequestDtoList = workoutPlanRequestServiceI.getWorkoutPlanRequest();
            return ResponseEntity.ok(workoutPlanRequestDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to load workout request records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/my-workout-plan/{id}")
    public ResponseEntity<WorkoutPlanRequestDto> deleteWorkoutPlanRequest(@PathVariable long id) {
        try {
            WorkoutPlanRequestDto workoutPlanRequestDto = workoutPlanRequestServiceI.deleteWorkoutPlanRequest(id);
            return ResponseEntity.ok(workoutPlanRequestDto);
        } catch (Exception e) {
            throw new AppException("Failed to delete the Workout Plan Request record. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }




}
}
