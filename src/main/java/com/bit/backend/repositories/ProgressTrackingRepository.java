package com.bit.backend.repositories;

import com.bit.backend.entities.ProgressTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProgressTrackingRepository extends JpaRepository<ProgressTrackingEntity, Long> {

    @Query("SELECT new map(p.date as date, MAX(p.weight) as weight) " +
            "FROM ProgressTrackingEntity p WHERE p.userName = :userName GROUP BY p.date")
    List<Map<String, Object>> getWeightOverTime(@Param("userName") String userName);
//
//    List<ProgressTrackingEntity> findByUserNameOrderByDateAsc(String userName);


    @Query("SELECT p.date as date, max(p.weight) as weight, max(p.bmi) as bmi, max(p.bodyFat) as bodyFat " +
            "FROM ProgressTrackingEntity p WHERE p.userName = :userName GROUP BY p.date ORDER BY p.date")
    List<Map<String, Object>> getWeightOverTimeByUser(@Param("userName") String userName);

    // Returns the most recent progress record for a member — used by meal plan auto-suggest to get latest BMI
    Optional<ProgressTrackingEntity> findTopByUserNameOrderByDateDesc(String userName);
}
