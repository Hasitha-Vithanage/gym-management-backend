package com.bit.backend.repositories;

import com.bit.backend.entities.MealPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface MealPlanRepository extends JpaRepository<MealPlanEntity, Long> {

    @Query("SELECT COUNT(m) > 0 FROM MealPlanEntity m WHERE m.username = :username")
    boolean existsByUsername(@Param("username") String username);

}
