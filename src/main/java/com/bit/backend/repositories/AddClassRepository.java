package com.bit.backend.repositories;

import com.bit.backend.entities.AddClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AddClassRepository extends JpaRepository<AddClassEntity, Long> {
    List<AddClassEntity> findAllByIsDeletedFalse();
    Optional<AddClassEntity> findByIdAndIsDeletedFalse(Long id);
    List<AddClassEntity> findByDateAndIsDeletedFalse(LocalDate date);
}
