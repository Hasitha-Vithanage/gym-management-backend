package com.bit.backend.services.impl;

import com.bit.backend.dtos.TemplateMealItemDto;
import com.bit.backend.entities.FoodItemEntity;
import com.bit.backend.entities.TemplateMealItemEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.TemplateMealItemMapper;
import com.bit.backend.repositories.FoodItemRepository;
import com.bit.backend.repositories.TemplateMealItemRepository;
import com.bit.backend.services.TemplateMealItemServiceI;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TemplateMealItemService implements TemplateMealItemServiceI {

    private final TemplateMealItemRepository mealItemRepository;
    private final TemplateMealItemMapper mealItemMapper;
    private final FoodItemRepository foodItemRepository;

    public TemplateMealItemService(TemplateMealItemRepository mealItemRepository,
                                   TemplateMealItemMapper mealItemMapper,
                                   FoodItemRepository foodItemRepository) {
        this.mealItemRepository = mealItemRepository;
        this.mealItemMapper = mealItemMapper;
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public List<TemplateMealItemDto> getMealItemsByTemplateId(Long templateId) {
        try {
            List<TemplateMealItemEntity> entities =
                    mealItemRepository.findByTemplateIdOrderByDayOfWeekAscMealOrderAsc(templateId);
            List<TemplateMealItemDto> dtos = mealItemMapper.toDtoList(entities);

            // Enrich with category and dietaryTags from the food item library
            List<Long> foodItemIds = entities.stream()
                    .map(TemplateMealItemEntity::getFoodItemId)
                    .collect(Collectors.toList());

            Map<Long, FoodItemEntity> foodItemMap = foodItemRepository.findAllById(foodItemIds)
                    .stream()
                    .collect(Collectors.toMap(FoodItemEntity::getId, f -> f));

            dtos.forEach(dto -> {
                FoodItemEntity food = foodItemMap.get(dto.getFoodItemId());
                if (food != null) {
                    dto.setCategory(food.getCategory());
                    dto.setDietaryTags(food.getDietaryTags());
                }
            });

            return dtos;
        } catch (Exception e) {
            throw new AppException("Failed to load meal items: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public List<TemplateMealItemDto> saveAllMealItems(Long templateId, List<TemplateMealItemDto> items) {
        try {
            mealItemRepository.deleteByTemplateId(templateId);

            List<TemplateMealItemEntity> entities = items.stream()
                    .map(dto -> {
                        dto.setTemplateId(templateId);
                        return mealItemMapper.toEntity(dto);
                    })
                    .toList();

            List<TemplateMealItemEntity> saved = mealItemRepository.saveAll(entities);
            return mealItemMapper.toDtoList(saved);
        } catch (Exception e) {
            throw new AppException("Failed to save meal items: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteMealItem(Long id) {
        try {
            if (!mealItemRepository.existsById(id)) {
                throw new AppException("Meal item not found", HttpStatus.NOT_FOUND);
            }
            mealItemRepository.deleteById(id);
        } catch (Exception e) {
            throw new AppException("Failed to remove meal item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
