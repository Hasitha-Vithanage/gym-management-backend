package com.bit.backend.repositories;

import com.bit.backend.entities.AddClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddClassRepository extends JpaRepository<AddClassEntity, Long> {
    List<AddClassEntity> findAllByIsDeletedFalseOrIsDeletedIsNull();

    @Query("SELECT COUNT(c) > 0 FROM AddClassEntity c WHERE c.trainerEmployeeId = :trainerId AND c.status = 'Scheduled' AND (c.isDeleted = false OR c.isDeleted IS NULL)")
    boolean existsScheduledClassByTrainer(@Param("trainerId") long trainerId);
}
