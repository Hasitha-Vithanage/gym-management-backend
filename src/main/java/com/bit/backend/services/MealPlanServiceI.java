package com.bit.backend.services;

import com.bit.backend.dtos.MealPlanDto;
import com.bit.backend.dtos.MealPlanUploadDto;
import com.bit.backend.dtos.WorkoutPlanDto;
import com.bit.backend.dtos.WorkoutPlanRequestDto;

import java.util.List;

public interface MealPlanServiceI {

    MealPlanDto addMealPlanEntry(MealPlanDto mealPlanDto);
    List<MealPlanDto> getAllMealPlanEntries();
    boolean hasRequestedMealPlan(String userId);

//    MealPlanDto updateStatus(String userId);
}
