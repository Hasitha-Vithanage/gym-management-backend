package com.bit.backend.repositories;

import com.bit.backend.entities.MemberNutritionProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberNutritionProfileRepository extends JpaRepository<MemberNutritionProfileEntity, Long> {

    @Query("SELECT COUNT(p) > 0 FROM MemberNutritionProfileEntity p WHERE p.userId = :userId")
    boolean existsByUserId(@Param("userId") String userId);

    Optional<MemberNutritionProfileEntity> findTopByUserIdOrderByIdDesc(String userId);
}
