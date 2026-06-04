package com.bit.backend.repositories;

import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.BookClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookClassRepository extends JpaRepository<BookClassEntity, Long> {

    List<BookClassEntity> findByBookedByAndClassId(String bookedBy, long classId);
    List<BookClassEntity> findByUserIdAndClassId(Long userId, long classId);
    List<BookClassEntity> findByUserId(Long userId);
}
