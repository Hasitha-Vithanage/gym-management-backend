package com.bit.backend.services.impl;

import com.bit.backend.dtos.FoodItemDto;
import com.bit.backend.entities.FoodItemEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.FoodItemMapper;
import com.bit.backend.repositories.FoodItemRepository;
import com.bit.backend.services.FoodItemServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService implements FoodItemServiceI {

    private final FoodItemRepository repository;
    private final FoodItemMapper mapper;

    public FoodItemService(FoodItemRepository repository, FoodItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FoodItemDto createFoodItem(FoodItemDto dto) {
        try {
            FoodItemEntity entity = mapper.toEntity(dto);
            FoodItemEntity saved = repository.save(entity);
            return mapper.toDto(saved);
        } catch (Exception e) {
            throw new AppException("Failed to create food item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<FoodItemDto> getAllFoodItems() {
        try {
            return mapper.toDtoList(repository.findAllByIsDeletedFalse());
        } catch (Exception e) {
            throw new AppException("Failed to load food items: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FoodItemDto updateFoodItem(Long id, FoodItemDto dto) {
        try {
            repository.findById(id)
                    .orElseThrow(() -> new AppException("Food item not found", HttpStatus.BAD_REQUEST));

            FoodItemEntity updated = mapper.toEntity(dto);
            updated.setId(id);
            return mapper.toDto(repository.save(updated));
        } catch (Exception e) {
            throw new AppException("Failed to update food item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FoodItemDto deleteFoodItem(Long id) {
        try {
            FoodItemEntity entity = repository.findById(id)
                    .orElseThrow(() -> new AppException("Food item not found", HttpStatus.BAD_REQUEST));

            entity.setDeleted(true);
            return mapper.toDto(repository.save(entity));
        } catch (Exception e) {
            throw new AppException("Failed to delete food item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
