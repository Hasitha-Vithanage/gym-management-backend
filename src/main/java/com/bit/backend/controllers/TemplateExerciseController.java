package com.bit.backend.controllers;

import com.bit.backend.dtos.TemplateExerciseDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.TemplateExerciseServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TemplateExerciseController {

    private final TemplateExerciseServiceI templateExerciseServiceI;

    public TemplateExerciseController(TemplateExerciseServiceI templateExerciseServiceI) {
        this.templateExerciseServiceI = templateExerciseServiceI;
    }

    @GetMapping("/workout-templates/{templateId}/exercises")
    public ResponseEntity<List<TemplateExerciseDto>> getExercises(@PathVariable Long templateId) {
        try {
            return ResponseEntity.ok(templateExerciseServiceI.getExercisesByTemplateId(templateId));
        } catch (Exception e) {
            throw new AppException("Failed to load exercises for template. " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/workout-templates/{templateId}/exercises")
    public ResponseEntity<List<TemplateExerciseDto>> saveExercises(
            @PathVariable Long templateId,
            @RequestBody List<TemplateExerciseDto> exercises) {
        try {
            List<TemplateExerciseDto> saved = templateExerciseServiceI.saveAllExercises(templateId, exercises);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            throw new AppException("Failed to save exercises for template. " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/workout-templates/{templateId}/exercises/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long templateId, @PathVariable Long id) {
        try {
            templateExerciseServiceI.deleteExercise(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new AppException("Failed to remove exercise from template. " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
