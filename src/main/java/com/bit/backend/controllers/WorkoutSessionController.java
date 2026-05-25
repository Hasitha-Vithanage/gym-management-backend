package com.bit.backend.controllers;

import com.bit.backend.dtos.MemberProgressSummaryDto;
import com.bit.backend.dtos.WorkoutSessionDto;
import com.bit.backend.dtos.WorkoutSessionExerciseDto;
import com.bit.backend.dtos.WorkoutSessionSummaryDto;
import com.bit.backend.services.WorkoutSessionServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class WorkoutSessionController {

    private final WorkoutSessionServiceI sessionService;

    public WorkoutSessionController(WorkoutSessionServiceI sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/workout-sessions")
    public ResponseEntity<WorkoutSessionDto> createSession(@RequestBody WorkoutSessionDto dto) {
        return ResponseEntity.ok(sessionService.createSession(dto));
    }

    @PutMapping("/workout-sessions/{id}/complete")
    public ResponseEntity<WorkoutSessionDto> completeSession(
            @PathVariable Long id,
            @RequestBody List<WorkoutSessionExerciseDto> exercises) {
        return ResponseEntity.ok(sessionService.completeSession(id, exercises));
    }

    @GetMapping("/workout-sessions/member/{memberId}/summary")
    public ResponseEntity<WorkoutSessionSummaryDto> getMemberSummary(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long assignmentId) {
        return ResponseEntity.ok(sessionService.getMemberSummary(memberId, assignmentId));
    }

    @GetMapping("/workout-sessions/member/{memberId}")
    public ResponseEntity<List<WorkoutSessionDto>> getMemberSessions(@PathVariable Long memberId) {
        return ResponseEntity.ok(sessionService.getMemberSessions(memberId));
    }

    @GetMapping("/workout-sessions/assignment/{assignmentId}/completed-days")
    public ResponseEntity<List<Integer>> getCompletedDays(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(sessionService.getCompletedWorkoutDays(assignmentId));
    }

    @GetMapping("/workout-sessions/assignment/{assignmentId}/weekly-frequency")
    public ResponseEntity<List<Map<String, Object>>> getWeeklyFrequency(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(sessionService.getWeeklyFrequency(assignmentId));
    }

    @GetMapping("/workout-sessions/member/{memberId}/exercise-performance")
    public ResponseEntity<List<Map<String, Object>>> getExercisePerformance(
            @PathVariable Long memberId,
            @RequestParam Long exerciseId) {
        return ResponseEntity.ok(sessionService.getExercisePerformance(memberId, exerciseId));
    }

    @GetMapping("/workout-sessions/members-progress")
    public ResponseEntity<List<MemberProgressSummaryDto>> getAllMembersProgress() {
        return ResponseEntity.ok(sessionService.getAllMembersProgress());
    }
}
