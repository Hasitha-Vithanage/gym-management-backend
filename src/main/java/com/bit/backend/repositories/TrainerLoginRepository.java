package com.bit.backend.repositories;

import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.TrainerLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerLoginRepository extends JpaRepository<TrainerLoginEntity, Long> {
}
