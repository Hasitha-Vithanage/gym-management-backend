package com.bit.backend.repositories;

import com.bit.backend.entities.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {

    boolean existsByExerciseNameIgnoreCaseAndIsDeletedFalse(String exerciseName);

    boolean existsByExerciseNameIgnoreCaseAndIsDeletedFalseAndIdNot(String exerciseName, Long id);
}
