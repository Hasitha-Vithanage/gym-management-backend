package com.bit.backend.repositories;

import com.bit.backend.entities.MealPlanTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealPlanTemplateRepository extends JpaRepository<MealPlanTemplateEntity, Long> {

    List<MealPlanTemplateEntity> findAllByIsDeletedFalse();

    // Used by Phase 4 auto-suggest: filter active templates where suitableGoals contains the member's goal
    @Query("SELECT DISTINCT t FROM MealPlanTemplateEntity t " +
           "JOIN t.suitableGoals g " +
           "WHERE t.isDeleted = false AND g = :goal")
    List<MealPlanTemplateEntity> findActiveByGoal(@Param("goal") String goal);

    // Used by Phase 4 auto-suggest: filter active templates by both goal and BMI category
    @Query("SELECT DISTINCT t FROM MealPlanTemplateEntity t " +
           "JOIN t.suitableGoals g " +
           "JOIN t.suitableBmiCategories b " +
           "WHERE t.isDeleted = false AND g = :goal AND b = :bmiCategory")
    List<MealPlanTemplateEntity> findActiveByGoalAndBmi(
            @Param("goal") String goal,
            @Param("bmiCategory") String bmiCategory);


//    @Query("SELECT DISTINCT t FROM MealPlanTemplateEntity t " +
//            "JOIN t.suitableGoals g " +
//            "JOIN t.suitableBmiCategories b " +
//            "WHERE t.isDeleted = false AND g = :goal AND b = :bmiCategory AND NOT EXISTS (SELECT t2 FROM MealPlanTemplateEntity t2 JOIN t2.allergies a WHERE t2 = t AND " +
//            "a IN :memberAllergiess)")
//    List<MealPlanTemplateEntity> findActiveByGoalAndBmi(
//            @Param("goal") String goal,
//            @Param("bmiCategory") String bmiCategory,
//            @Param("memberAllergiess") List<String> memberAllergiess);
}
