package com.bit.backend.repositories;

import com.bit.backend.entities.WorkoutPlanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRequestRepository extends JpaRepository<WorkoutPlanRequestEntity, Long> {


    Optional<WorkoutPlanRequestEntity> findByUserId(String userId);

    Optional<WorkoutPlanRequestEntity> findTopByUserIdOrderByIdDesc(String userId);

    List<WorkoutPlanRequestEntity> findByStatus(String status);

    List<WorkoutPlanRequestEntity> findByStatusAndMemberUserIdIn(String status, List<Long> memberUserIds);

}
