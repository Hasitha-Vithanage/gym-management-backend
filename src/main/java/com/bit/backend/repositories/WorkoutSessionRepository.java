package com.bit.backend.repositories;

import com.bit.backend.entities.WorkoutSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSessionEntity, Long> {

    List<WorkoutSessionEntity> findByMemberIdOrderBySessionDateDesc(Long memberId);

    List<WorkoutSessionEntity> findByAssignmentId(Long assignmentId);

    List<WorkoutSessionEntity> findByAssignmentIdOrderBySessionDateDescIdDesc(Long assignmentId);

    @Query("SELECT s FROM WorkoutSessionEntity s WHERE s.memberId = :memberId AND s.sessionDate >= :startDate ORDER BY s.sessionDate DESC")
    List<WorkoutSessionEntity> findByMemberIdSince(@Param("memberId") Long memberId, @Param("startDate") LocalDate startDate);

    @Query("SELECT DISTINCT s.workoutDay FROM WorkoutSessionEntity s WHERE s.assignmentId = :assignmentId AND s.status = 'COMPLETED'")
    List<Integer> findCompletedWorkoutDaysByAssignmentId(@Param("assignmentId") Long assignmentId);

    @Query(value = "SELECT YEARWEEK(s.session_date, 1) AS week_num, COUNT(*) AS session_count, MIN(s.session_date) AS week_start " +
                   "FROM workout_session s " +
                   "WHERE s.assignment_id = :assignmentId " +
                   "GROUP BY YEARWEEK(s.session_date, 1) " +
                   "ORDER BY week_num ASC", nativeQuery = true)
    List<Map<String, Object>> findWeeklyFrequency(@Param("assignmentId") Long assignmentId);
}
