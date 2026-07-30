package com.bit.backend.repositories;

import com.bit.backend.entities.WorkoutTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutTemplateRepository extends JpaRepository<WorkoutTemplateEntity, Long> {

    boolean existsByTemplateNameIgnoreCaseAndIsDeletedFalse(String templateName);

    boolean existsByTemplateNameIgnoreCaseAndIsDeletedFalseAndIdNot(String templateName, Long id);
}

