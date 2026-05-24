package com.bit.backend.controllers;

import com.bit.backend.dtos.WorkoutTemplateDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.WorkoutTemplateServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class WorkoutTemplateController {
    private final WorkoutTemplateServiceI workoutTemplateServiceI;

    public WorkoutTemplateController(WorkoutTemplateServiceI workoutTemplateServiceI) {
        this.workoutTemplateServiceI = workoutTemplateServiceI;
    }

    @PostMapping(value = "/workout-templates")
    public ResponseEntity<WorkoutTemplateDto> createWorkoutTemplate(@RequestPart("templateForm") WorkoutTemplateDto workoutTemplateDto) {
        try {
            WorkoutTemplateDto workoutTemplateDtoResponse = workoutTemplateServiceI.createWorkoutTemplateEntity(workoutTemplateDto);
            return ResponseEntity.created(URI.create("/workout-templates" + workoutTemplateDtoResponse.getId()))
                    .body(workoutTemplateDtoResponse);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("workout-templates/{id}")
    public ResponseEntity<WorkoutTemplateDto> updateWorkoutTemplate(@PathVariable long id, @RequestPart("templateForm") WorkoutTemplateDto workoutTemplateDto) {
        try {
            WorkoutTemplateDto workoutTemplateDtoResponse = workoutTemplateServiceI.editWorkoutTemplate(id, workoutTemplateDto);
            return ResponseEntity.ok(workoutTemplateDtoResponse);
        } catch (Exception e) {
            throw new AppException("Failed to update the workout template information. Please try again later." + e,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/workout-templates/{id}")
    public ResponseEntity<WorkoutTemplateDto> getWorkoutTemplateById(@PathVariable long id) {
        try {
            WorkoutTemplateDto dto = workoutTemplateServiceI.getWorkoutTemplateById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            throw new AppException("Failed to load workout template. Please try again later." + e,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/workout-templates")
    public ResponseEntity<List<WorkoutTemplateDto>> getAllExercise() {
        try {
            List<WorkoutTemplateDto> workoutTemplateDtoList = workoutTemplateServiceI.getAllWorkoutTemplates();
            return ResponseEntity.ok(workoutTemplateDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to load workout template records. Please try again later." + e,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/workout-templates/delete/{id}")
    public ResponseEntity<WorkoutTemplateDto> deleteWorkoutTemplate(@PathVariable long id, @RequestBody Map<String, Object> request) {
        try {
            boolean isDelete = (boolean) request.getOrDefault("isDelete", false);

            if (isDelete) {
                throw new AppException("Invalid delete request", HttpStatus.BAD_REQUEST);
            }

            WorkoutTemplateDto workoutTemplateDto = workoutTemplateServiceI.deleteWorkoutTemplate(id);
            return ResponseEntity.ok(workoutTemplateDto);

        } catch (Exception e) {
            throw new AppException("Failed to delete the workout template record. Please try again later." + e,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
