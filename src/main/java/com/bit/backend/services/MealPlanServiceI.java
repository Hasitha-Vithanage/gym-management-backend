package com.bit.backend.services;

import com.bit.backend.dtos.MealPlanDto;
import com.bit.backend.dtos.MealPlanUploadDto;
import com.bit.backend.dtos.WorkoutPlanDto;

import java.util.List;

public interface MealPlanServiceI {

    MealPlanDto addMealPlanEntry(MealPlanDto mealPlanDto);
    List<MealPlanDto> getAllMealPlanEntries();
    boolean hasRequestedMealPlan(String username);

//    void updateStatus(String userId);
}
