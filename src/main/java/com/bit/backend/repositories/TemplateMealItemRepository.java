package com.bit.backend.repositories;

import com.bit.backend.entities.TemplateMealItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateMealItemRepository extends JpaRepository<TemplateMealItemEntity, Long> {

    List<TemplateMealItemEntity> findByTemplateIdOrderByDayOfWeekAscMealOrderAsc(Long templateId);

    void deleteByTemplateId(Long templateId);

    int countByTemplateId(Long templateId);
}
