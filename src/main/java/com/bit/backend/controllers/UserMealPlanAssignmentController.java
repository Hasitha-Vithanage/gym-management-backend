package com.bit.backend.controllers;

import com.bit.backend.dtos.SuggestedMealPlanDto;
import com.bit.backend.dtos.UserMealPlanAssignmentDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.UserMealPlanAssignmentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-meal-plan-assignment")
public class UserMealPlanAssignmentController {

    private final UserMealPlanAssignmentServiceI service;

    public UserMealPlanAssignmentController(UserMealPlanAssignmentServiceI service) {
        this.service = service;
    }

    /**
     * POST /user-meal-plan-assignment
     * Body: { "userId": "john", "templateId": 3, "assignedBy": "trainerJane", "durationWeeks": 4 }
     */
    @PostMapping
    public ResponseEntity<UserMealPlanAssignmentDto> createAssignment(@RequestBody Map<String, Object> body) {
        try {
            String userId = body.get("userId").toString();
            Long templateId = Long.parseLong(body.get("templateId").toString());
            String assignedBy = body.get("assignedBy") != null ? body.get("assignedBy").toString() : null;
            Integer durationWeeks = body.get("durationWeeks") != null
                    ? Integer.parseInt(body.get("durationWeeks").toString())
                    : null;

            UserMealPlanAssignmentDto result = service.createAssignment(userId, templateId, assignedBy, durationWeeks);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            throw new AppException("Failed to create meal plan assignment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET /user-meal-plan-assignment/active/{userId}
     * Returns the current Active assignment with nested template, or 204 No Content if none.
     */
    @GetMapping("/active/{userId}")
    public ResponseEntity<UserMealPlanAssignmentDto> getActiveAssignment(@PathVariable String userId) {
        try {
            UserMealPlanAssignmentDto dto = service.getActiveAssignment(userId);
            if (dto == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            throw new AppException("Failed to load active assignment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET /user-meal-plan-assignment/all/{userId}
     * Returns full assignment history for a member.
     */
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<UserMealPlanAssignmentDto>> getAllAssignments(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(service.getAllAssignmentsForMember(userId));
        } catch (Exception e) {
            throw new AppException("Failed to load assignments: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET /user-meal-plan-assignment/suggest/{userId}
     * Returns ranked list of suggested templates based on the member's profile and BMI.
     * Used by the trainer when assigning a meal plan.
     */
    @GetMapping("/suggest/{userId}")
    public ResponseEntity<List<SuggestedMealPlanDto>> suggestTemplates(@PathVariable String userId) {
        try {
            List<SuggestedMealPlanDto> suggestions = service.suggestTemplates(userId);
            return ResponseEntity.ok(suggestions);
        } catch (Exception e) {
            throw new AppException("Failed to generate suggestions: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
