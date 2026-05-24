package com.bit.backend.repositories;

import com.bit.backend.entities.UserWorkoutAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserWorkoutAssignmentRepository extends JpaRepository<UserWorkoutAssignmentEntity, Long> {

    // Finds the most recent active assignment for a user
    Optional<UserWorkoutAssignmentEntity> findTopByUserIdAndStatusOrderByCreatedAtDesc(Long userId, String status);

    // Used to bulk-mark all active assignments as Replaced before creating a new one
    List<UserWorkoutAssignmentEntity> findByUserIdAndStatus(Long userId, String status);
}
