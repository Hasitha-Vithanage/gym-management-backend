package com.bit.backend.services;

import com.bit.backend.dtos.MemberProgressSummaryDto;
import com.bit.backend.dtos.WorkoutSessionDto;
import com.bit.backend.dtos.WorkoutSessionExerciseDto;
import com.bit.backend.dtos.WorkoutSessionSummaryDto;

import java.util.List;
import java.util.Map;

public interface WorkoutSessionServiceI {

    WorkoutSessionDto createSession(WorkoutSessionDto dto);

    WorkoutSessionDto completeSession(Long sessionId, List<WorkoutSessionExerciseDto> exercises);

    void cancelSession(Long sessionId);

    WorkoutSessionSummaryDto getMemberSummary(Long memberId, Long assignmentId);

    List<WorkoutSessionDto> getMemberSessions(Long memberId);

    List<Integer> getCompletedWorkoutDays(Long assignmentId);

    List<Map<String, Object>> getWeeklyFrequency(Long assignmentId);

    List<Map<String, Object>> getExercisePerformance(Long memberId, Long exerciseId);

    List<MemberProgressSummaryDto> getAllMembersProgress();
}
