package com.bit.backend.repositories;

import com.bit.backend.entities.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {

    List<FeedbackEntity> findBySubmittedBy(String submittedBy);

    long countByStatus(String status);

    @Query("SELECT f.targetName, AVG(f.rating), COUNT(f) FROM FeedbackEntity f WHERE f.category = 'Trainer' GROUP BY f.targetName")
    List<Object[]> findTrainerRatings();
}
