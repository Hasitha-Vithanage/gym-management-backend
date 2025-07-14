package com.bit.backend.services.impl;

import com.bit.backend.dtos.MealPlanUploadDto;
import com.bit.backend.dtos.WorkoutPlanDto;
import com.bit.backend.entities.MealPlanUploadEntity;
import com.bit.backend.entities.WorkoutPlanEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MealPlanUploadMapper;
import com.bit.backend.repositories.MealPlanRepository;
import com.bit.backend.repositories.MealPlanUploadRepository;
import com.bit.backend.services.MealPlanUploadServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MealPlanUploadService implements MealPlanUploadServiceI {

    private final MealPlanUploadRepository mealPlanUploadRepository;
    private final MealPlanUploadMapper mealPlanUploadMapper;

    public MealPlanUploadService(MealPlanUploadRepository mealPlanUploadRepository, MealPlanUploadMapper mealPlanUploadMapper) {
        this.mealPlanUploadRepository = mealPlanUploadRepository;
        this.mealPlanUploadMapper = mealPlanUploadMapper;
    }


    @Override
    public MealPlanUploadDto addMealPlanUploadEntity(MealPlanUploadDto mealPlanUploadDto) {
        System.out.println("In the addMemberEntity method");

        MealPlanUploadEntity mealPlanUploadEntity = mealPlanUploadMapper.toMealPlanUploadEntity(mealPlanUploadDto);
        MealPlanUploadEntity savedItem = mealPlanUploadRepository.save(mealPlanUploadEntity);
        MealPlanUploadDto savedDto = mealPlanUploadMapper.toMealPlanUploadDto(savedItem);
        return savedDto;
    }

    @Override
    public MealPlanUploadDto getMealPlanUploadByUserId(String userId) {

        try {
            MealPlanUploadEntity entity = mealPlanUploadRepository.findTopByUserIdOrderByIdDesc(userId)
                    .orElseThrow(() -> new AppException("Meal plan not found for user: " + userId, HttpStatus.NOT_FOUND));
            return mealPlanUploadMapper.toMealPlanUploadDto(entity);
        } catch (Exception e) {
            throw new AppException("Error fetching meal plan: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
