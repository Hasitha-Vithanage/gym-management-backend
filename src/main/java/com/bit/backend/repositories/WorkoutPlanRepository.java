package com.bit.backend.repositories;

import com.bit.backend.entities.WorkoutPlanEntity;
import com.bit.backend.entities.WorkoutPlanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlanEntity, Long> {

    Optional<WorkoutPlanEntity> findTopByUserIdOrderByIdDesc(String userId);
}
