package com.bit.backend.services.impl;

import com.bit.backend.dtos.MemberProgressSummaryDto;
import com.bit.backend.dtos.WorkoutSessionDto;
import com.bit.backend.dtos.WorkoutSessionExerciseDto;
import com.bit.backend.dtos.WorkoutSessionSummaryDto;
import com.bit.backend.entities.User;
import com.bit.backend.entities.UserWorkoutAssignmentEntity;
import com.bit.backend.entities.WorkoutSessionEntity;
import com.bit.backend.entities.WorkoutSessionExerciseEntity;
import com.bit.backend.entities.WorkoutTemplateEntity;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.repositories.UserWorkoutAssignmentRepository;
import com.bit.backend.repositories.WorkoutSessionExerciseRepository;
import com.bit.backend.repositories.WorkoutSessionRepository;
import com.bit.backend.repositories.WorkoutTemplateRepository;
import com.bit.backend.services.WorkoutSessionServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutSessionService implements WorkoutSessionServiceI {

    private final WorkoutSessionRepository sessionRepo;
    private final WorkoutSessionExerciseRepository exerciseRepo;
    private final UserWorkoutAssignmentRepository assignmentRepo;
    private final WorkoutTemplateRepository templateRepo;
    private final UserRepository userRepo;

    public WorkoutSessionService(WorkoutSessionRepository sessionRepo,
                                 WorkoutSessionExerciseRepository exerciseRepo,
                                 UserWorkoutAssignmentRepository assignmentRepo,
                                 WorkoutTemplateRepository templateRepo,
                                 UserRepository userRepo) {
        this.sessionRepo = sessionRepo;
        this.exerciseRepo = exerciseRepo;
        this.assignmentRepo = assignmentRepo;
        this.templateRepo = templateRepo;
        this.userRepo = userRepo;
    }

    @Override
    public WorkoutSessionDto createSession(WorkoutSessionDto dto) {
        WorkoutSessionEntity entity = new WorkoutSessionEntity();
        entity.setAssignmentId(dto.getAssignmentId());
        entity.setMemberId(dto.getMemberId());
        entity.setWorkoutDay(dto.getWorkoutDay());
        entity.setSessionDate(dto.getSessionDate() != null ? dto.getSessionDate() : LocalDate.now());
        entity.setStatus("PARTIAL");
        entity.setNotes(dto.getNotes());

        WorkoutSessionEntity saved = sessionRepo.save(entity);
        return toDto(saved);
    }

    @Override
    @Transactional
    public WorkoutSessionDto completeSession(Long sessionId, List<WorkoutSessionExerciseDto> exercises) {
        WorkoutSessionEntity session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        exerciseRepo.deleteBySessionId(sessionId);

        if (exercises != null) {
            for (WorkoutSessionExerciseDto exDto : exercises) {
                WorkoutSessionExerciseEntity ex = new WorkoutSessionExerciseEntity();
                ex.setSessionId(sessionId);
                ex.setTemplateExerciseId(exDto.getTemplateExerciseId());
                ex.setExerciseId(exDto.getExerciseId());
                ex.setExerciseName(exDto.getExerciseName());
                ex.setSetsCompleted(exDto.getSetsCompleted());
                ex.setRepsLogged(exDto.getRepsLogged());
                ex.setWeightKg(exDto.getWeightKg());
                ex.setCompleted(exDto.getCompleted());
                exerciseRepo.save(ex);
            }
        }

        session.setStatus("COMPLETED");
        WorkoutSessionEntity updated = sessionRepo.save(session);
        return toDto(updated);
    }

    @Override
    public void cancelSession(Long sessionId) {
        sessionRepo.findById(sessionId).ifPresent(session -> {
            // Only a still-in-progress session can be cancelled this way — a session
            // already marked COMPLETED must never be removed through this endpoint.
            if ("PARTIAL".equals(session.getStatus())) {
                sessionRepo.deleteById(sessionId);
            }
        });
    }

    @Override
    public WorkoutSessionSummaryDto getMemberSummary(Long memberId, Long assignmentId) {
        WorkoutSessionSummaryDto summary = new WorkoutSessionSummaryDto();
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY);

        // Scope all session stats to the current assignment so cycle tracking is accurate.
        // Secondary sort by id DESC resolves ties when multiple sessions share the same date.
        List<WorkoutSessionEntity> assignmentSessions = (assignmentId != null
                ? sessionRepo.findByAssignmentIdOrderBySessionDateDescIdDesc(assignmentId)
                : sessionRepo.findByMemberIdOrderBySessionDateDesc(memberId))
                .stream()
                .filter(s -> "COMPLETED".equals(s.getStatus()))
                .collect(Collectors.toList());

        summary.setTotalSessionsCompleted(assignmentSessions.size());

        if (!assignmentSessions.isEmpty()) {
            WorkoutSessionEntity mostRecent = assignmentSessions.get(0);
            summary.setLastSessionDate(mostRecent.getSessionDate());
            summary.setDaysSinceLastSession((int) ChronoUnit.DAYS.between(mostRecent.getSessionDate(), today));
            summary.setLastWorkoutDay(mostRecent.getWorkoutDay());
        }

        long sessionsThisWeek = assignmentSessions.stream()
                .filter(s -> !s.getSessionDate().isBefore(monday))
                .count();
        summary.setSessionsThisWeek((int) sessionsThisWeek);

        if (assignmentId != null) {
            Optional<UserWorkoutAssignmentEntity> assignmentOpt = assignmentRepo.findById(assignmentId);
            assignmentOpt.ifPresent(assignment -> {
                long weeksElapsed = ChronoUnit.WEEKS.between(assignment.getStartDate(), today);
                summary.setCurrentProgramWeek((int) weeksElapsed + 1);

                if (assignment.getEndDate() != null) {
                    long totalWeeks = ChronoUnit.WEEKS.between(assignment.getStartDate(), assignment.getEndDate());
                    summary.setTotalProgramWeeks((int) Math.max(totalWeeks, 1));
                }

                Optional<WorkoutTemplateEntity> templateOpt = templateRepo.findById(assignment.getTemplateId());
                templateOpt.ifPresent(template -> {
                    summary.setTargetSessionsPerWeek(template.getDaysPerWeek());
                    if (assignment.getEndDate() == null && template.getProgramLengthWeeks() != null) {
                        summary.setTotalProgramWeeks(template.getProgramLengthWeeks());
                    }
                });
            });

            List<Integer> completedDays = sessionRepo.findCompletedWorkoutDaysByAssignmentId(assignmentId);
            summary.setCompletedWorkoutDays(completedDays);
        }

        return summary;
    }

    @Override
    public List<WorkoutSessionDto> getMemberSessions(Long memberId) {
        return sessionRepo.findByMemberIdOrderBySessionDateDesc(memberId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getCompletedWorkoutDays(Long assignmentId) {
        return sessionRepo.findCompletedWorkoutDaysByAssignmentId(assignmentId);
    }

    @Override
    public List<Map<String, Object>> getWeeklyFrequency(Long assignmentId) {
        return sessionRepo.findWeeklyFrequency(assignmentId);
    }

    @Override
    public List<Map<String, Object>> getExercisePerformance(Long memberId, Long exerciseId) {
        return exerciseRepo.findExercisePerformance(memberId, exerciseId);
    }

    @Override
    public List<MemberProgressSummaryDto> getAllMembersProgress() {
        List<UserWorkoutAssignmentEntity> activeAssignments = assignmentRepo.findByStatus("Active");
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY);

        List<MemberProgressSummaryDto> result = new ArrayList<>();

        for (UserWorkoutAssignmentEntity assignment : activeAssignments) {
            MemberProgressSummaryDto dto = new MemberProgressSummaryDto();
            dto.setUserId(assignment.getUserId());
            dto.setProgramStartDate(assignment.getStartDate());

            userRepo.findById(assignment.getUserId()).ifPresent(user ->
                dto.setMemberName((user.getFirstName() + " " + user.getLastName()).trim()));

            long weeksElapsed = ChronoUnit.WEEKS.between(assignment.getStartDate(), today);
            dto.setCurrentProgramWeek((int) weeksElapsed + 1);

            templateRepo.findById(assignment.getTemplateId()).ifPresent(template -> {
                dto.setProgramName(template.getTemplateName());
                dto.setDaysPerWeek(template.getDaysPerWeek());
                if (assignment.getEndDate() == null && template.getProgramLengthWeeks() != null) {
                    dto.setTotalProgramWeeks(template.getProgramLengthWeeks());
                }
            });

            if (assignment.getEndDate() != null) {
                long totalWeeks = ChronoUnit.WEEKS.between(assignment.getStartDate(), assignment.getEndDate());
                dto.setTotalProgramWeeks((int) Math.max(totalWeeks, 1));
            }

            List<WorkoutSessionEntity> sessions = sessionRepo.findByMemberIdOrderBySessionDateDesc(assignment.getUserId());

            long sessionsThisWeek = sessions.stream()
                    .filter(s -> !s.getSessionDate().isBefore(monday))
                    .count();
            dto.setSessionsThisWeek((int) sessionsThisWeek);

            if (!sessions.isEmpty()) {
                LocalDate lastDate = sessions.get(0).getSessionDate();
                dto.setLastSessionDate(lastDate);
                int daysSince = (int) ChronoUnit.DAYS.between(lastDate, today);
                dto.setDaysSinceLastSession(daysSince);
                dto.setActivityStatus(resolveActivityStatus(daysSince));
            } else {
                dto.setDaysSinceLastSession(null);
                dto.setActivityStatus("NO_SESSIONS");
            }

            result.add(dto);
        }

        return result;
    }

    private String resolveActivityStatus(int daysSinceLastSession) {
        if (daysSinceLastSession <= 3) return "ACTIVE";
        if (daysSinceLastSession <= 7) return "INACTIVE";
        return "DROPPED_OFF";
    }

    private WorkoutSessionDto toDto(WorkoutSessionEntity entity) {
        WorkoutSessionDto dto = new WorkoutSessionDto();
        dto.setId(entity.getId());
        dto.setAssignmentId(entity.getAssignmentId());
        dto.setMemberId(entity.getMemberId());
        dto.setWorkoutDay(entity.getWorkoutDay());
        dto.setSessionDate(entity.getSessionDate());
        dto.setStatus(entity.getStatus());
        dto.setNotes(entity.getNotes());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
