package com.bit.backend.repositories;

import com.bit.backend.entities.MealPlanUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealPlanUploadRepository extends JpaRepository<MealPlanUploadEntity, Long> {

    Optional<MealPlanUploadEntity> findTopByUserIdOrderByIdDesc(String userId);
}
