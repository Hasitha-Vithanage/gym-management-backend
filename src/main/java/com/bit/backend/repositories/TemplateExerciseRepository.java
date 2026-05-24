package com.bit.backend.repositories;

import com.bit.backend.entities.TemplateExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateExerciseRepository extends JpaRepository<TemplateExerciseEntity, Long> {

    List<TemplateExerciseEntity> findByTemplateId(Long templateId);

    void deleteByTemplateId(Long templateId);

    int countByTemplateId(Long templateId);
}
