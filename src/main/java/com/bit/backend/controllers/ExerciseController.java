package com.bit.backend.controllers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.ExerciseDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.EmployeeServiceI;
import com.bit.backend.services.ExerciseServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class ExerciseController {

    private final ExerciseServiceI exerciseServiceI;

    public ExerciseController(ExerciseServiceI exerciseServiceI) {
        this.exerciseServiceI = exerciseServiceI;
    }

    @PostMapping(value = "/exercises")
    public ResponseEntity<ExerciseDto> createExercise(@RequestPart("exerciseForm") ExerciseDto exerciseDto) {
        try {
            ExerciseDto exerciseDtoResponse = exerciseServiceI.createExerciseEntity(exerciseDto);
            return ResponseEntity.created(URI.create("/exercise" + exerciseDtoResponse.getExerciseName()))
                    .body(exerciseDtoResponse);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("exercises/{id}")
    public ResponseEntity<ExerciseDto> updateExercise(@PathVariable long id, @RequestPart("exerciseForm") ExerciseDto exerciseDto) {
        try {
            ExerciseDto exerciseDtoResponse = exerciseServiceI.editExercise(id, exerciseDto);
            return ResponseEntity.ok(exerciseDtoResponse);
        } catch (Exception e) {
            throw new AppException("Failed to update the exercise information. Please try again later." + e,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseDto>> getAllExercise() {
        try {
            List<ExerciseDto> exercisesDtoList = exerciseServiceI.getAllExercises();
            return ResponseEntity.ok(exercisesDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to load exercises records. Please try again later." + e,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/exercises/delete/{id}")
    public ResponseEntity<ExerciseDto> deleteExercise(@PathVariable long id, @RequestBody Map<String, Object> request) {
        try {
            boolean isDelete = (boolean) request.getOrDefault("isDelete", false);

            if (isDelete) {
                throw new AppException("Invalid delete request", HttpStatus.BAD_REQUEST);
            }

            ExerciseDto exerciseDto = exerciseServiceI.deleteExercise(id);
            return ResponseEntity.ok(exerciseDto);

        } catch (Exception e) {
            throw new AppException("Failed to delete the exercise record. Please try again later." + e,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
