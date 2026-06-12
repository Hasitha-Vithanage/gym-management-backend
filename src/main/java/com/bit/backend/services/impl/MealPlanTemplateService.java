package com.bit.backend.services.impl;

import com.bit.backend.dtos.MealPlanTemplateDto;
import com.bit.backend.entities.MealPlanTemplateEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MealPlanTemplateMapper;
import com.bit.backend.repositories.MealPlanTemplateRepository;
import com.bit.backend.repositories.TemplateMealItemRepository;
import com.bit.backend.services.MealPlanTemplateServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealPlanTemplateService implements MealPlanTemplateServiceI {

    private final MealPlanTemplateRepository templateRepository;
    private final MealPlanTemplateMapper templateMapper;
    private final TemplateMealItemRepository mealItemRepository;

    public MealPlanTemplateService(MealPlanTemplateRepository templateRepository,
                                   MealPlanTemplateMapper templateMapper,
                                   TemplateMealItemRepository mealItemRepository) {
        this.templateRepository = templateRepository;
        this.templateMapper = templateMapper;
        this.mealItemRepository = mealItemRepository;
    }

    @Override
    public MealPlanTemplateDto createTemplate(MealPlanTemplateDto dto) {
        try {
            MealPlanTemplateEntity entity = templateMapper.toEntity(dto);
            MealPlanTemplateEntity saved = templateRepository.save(entity);
            return templateMapper.toDto(saved);
        } catch (Exception e) {
            throw new AppException("Failed to create meal plan template: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<MealPlanTemplateDto> getAllTemplates() {
        try {
            List<MealPlanTemplateDto> dtos = templateMapper.toDtoList(templateRepository.findAllByIsDeletedFalse());
            dtos.forEach(dto -> dto.setFoodItemCount(mealItemRepository.countByTemplateId(dto.getId())));
            return dtos;
        } catch (Exception e) {
            throw new AppException("Failed to load meal plan templates: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MealPlanTemplateDto getTemplateById(Long id) {
        try {
            MealPlanTemplateEntity entity = templateRepository.findById(id)
                    .orElseThrow(() -> new AppException("Meal plan template not found", HttpStatus.NOT_FOUND));
            MealPlanTemplateDto dto = templateMapper.toDto(entity);
            dto.setFoodItemCount(mealItemRepository.countByTemplateId(id));
            return dto;
        } catch (Exception e) {
            throw new AppException("Failed to load meal plan template: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MealPlanTemplateDto updateTemplate(Long id, MealPlanTemplateDto dto) {
        try {
            templateRepository.findById(id)
                    .orElseThrow(() -> new AppException("Meal plan template not found", HttpStatus.BAD_REQUEST));

            MealPlanTemplateEntity updated = templateMapper.toEntity(dto);
            updated.setId(id);
            return templateMapper.toDto(templateRepository.save(updated));
        } catch (Exception e) {
            throw new AppException("Failed to update meal plan template: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MealPlanTemplateDto deleteTemplate(Long id) {
        try {
            MealPlanTemplateEntity entity = templateRepository.findById(id)
                    .orElseThrow(() -> new AppException("Meal plan template not found", HttpStatus.BAD_REQUEST));

            entity.setDeleted(true);
            return templateMapper.toDto(templateRepository.save(entity));
        } catch (Exception e) {
            throw new AppException("Failed to delete meal plan template: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
