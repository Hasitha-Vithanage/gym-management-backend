package com.bit.backend.repositories;

import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.BookClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookClassRepository extends JpaRepository<BookClassEntity, Long> {
}
