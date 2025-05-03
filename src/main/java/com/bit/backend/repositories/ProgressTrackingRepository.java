package com.bit.backend.repositories;

import com.bit.backend.entities.ProgressTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressTrackingRepository extends JpaRepository<ProgressTrackingEntity, Long> {
}
