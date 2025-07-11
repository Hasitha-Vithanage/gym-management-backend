package com.bit.backend.repositories;

import com.bit.backend.entities.WorkoutPlanEntity;
import com.bit.backend.entities.WorkoutPlanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlanEntity, Long> {
}
