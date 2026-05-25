package com.bit.backend.repositories;

import com.bit.backend.entities.WorkoutSessionExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface WorkoutSessionExerciseRepository extends JpaRepository<WorkoutSessionExerciseEntity, Long> {

    List<WorkoutSessionExerciseEntity> findBySessionId(Long sessionId);

    void deleteBySessionId(Long sessionId);

    @Query(value = "SELECT s.session_date AS date, e.weight_kg AS weight, e.sets_completed AS sets " +
                   "FROM workout_session_exercise e " +
                   "JOIN workout_session s ON e.session_id = s.id " +
                   "WHERE s.member_id = :memberId AND e.exercise_id = :exerciseId " +
                   "ORDER BY s.session_date ASC", nativeQuery = true)
    List<Map<String, Object>> findExercisePerformance(@Param("memberId") Long memberId,
                                                      @Param("exerciseId") Long exerciseId);
}
