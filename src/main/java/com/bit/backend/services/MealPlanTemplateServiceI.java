package com.bit.backend.services;

import com.bit.backend.dtos.MealPlanTemplateDto;

import java.util.List;

public interface MealPlanTemplateServiceI {

    MealPlanTemplateDto createTemplate(MealPlanTemplateDto dto);
    List<MealPlanTemplateDto> getAllTemplates();
    MealPlanTemplateDto getTemplateById(Long id);
    MealPlanTemplateDto updateTemplate(Long id, MealPlanTemplateDto dto);
    MealPlanTemplateDto deleteTemplate(Long id);
}
