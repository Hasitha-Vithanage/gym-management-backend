package com.bit.backend.repositories;

import com.bit.backend.entities.TrainerLoginEntity;
import com.bit.backend.entities.WorkoutPlanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutPlanRequestRepository extends JpaRepository<WorkoutPlanRequestEntity, Long> {


    Optional<WorkoutPlanRequestEntity> findByUserId(String userId);

}
