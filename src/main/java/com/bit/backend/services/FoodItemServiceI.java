package com.bit.backend.services;

import com.bit.backend.dtos.FoodItemDto;

import java.util.List;

public interface FoodItemServiceI {

    FoodItemDto createFoodItem(FoodItemDto dto);
    List<FoodItemDto> getAllFoodItems();
    FoodItemDto updateFoodItem(Long id, FoodItemDto dto);
    FoodItemDto deleteFoodItem(Long id);
}
