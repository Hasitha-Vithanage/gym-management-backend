package com.bit.backend.services;

import com.bit.backend.dtos.MealPlanUploadDto;
import com.bit.backend.dtos.WorkoutPlanDto;

public interface MealPlanUploadServiceI {

    MealPlanUploadDto addMealPlanUploadEntity(MealPlanUploadDto mealPlanUploadDto);
    MealPlanUploadDto getMealPlanUploadByUserId(String userId);
}
