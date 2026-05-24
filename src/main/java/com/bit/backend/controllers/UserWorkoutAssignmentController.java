package com.bit.backend.controllers;

import com.bit.backend.dtos.UserWorkoutAssignmentDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.UserWorkoutAssignmentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user-workout-assignment")
public class UserWorkoutAssignmentController {

    private final UserWorkoutAssignmentServiceI assignmentService;

    public UserWorkoutAssignmentController(UserWorkoutAssignmentServiceI assignmentService) {
        this.assignmentService = assignmentService;
    }

    /**
     * POST /user-workout-assignment
     * Body: { "userId": 123, "templateId": 5, "programLengthWeeks": 4 }
     */
    @PostMapping
    public ResponseEntity<UserWorkoutAssignmentDto> createAssignment(@RequestBody Map<String, Object> body) {
        try {
            Long userId = Long.parseLong(body.get("userId").toString());
            Long templateId = Long.parseLong(body.get("templateId").toString());
            Integer programLengthWeeks = body.get("programLengthWeeks") != null
                    ? Integer.parseInt(body.get("programLengthWeeks").toString())
                    : null;

            UserWorkoutAssignmentDto result = assignmentService.createAssignment(userId, templateId, programLengthWeeks);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            throw new AppException("Failed to create workout assignment: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET /user-workout-assignment/active/{userId}
     * Returns the current Active assignment (with nested template), or 204 No Content if none.
     */
    @GetMapping("/active/{userId}")
    public ResponseEntity<UserWorkoutAssignmentDto> getActiveAssignment(@PathVariable Long userId) {
        try {
            UserWorkoutAssignmentDto dto = assignmentService.getActiveAssignment(userId);
            if (dto == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            throw new AppException("Failed to load active assignment: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
