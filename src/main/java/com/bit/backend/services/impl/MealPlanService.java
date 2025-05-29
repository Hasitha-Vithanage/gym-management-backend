package com.bit.backend.services.impl;

import com.bit.backend.dtos.MealPlanDto;
import com.bit.backend.entities.MealPlanEntity;
import com.bit.backend.mappers.MealPlanMapper;
import com.bit.backend.repositories.MealPlanRepository;
import com.bit.backend.services.MealPlanServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealPlanService  implements MealPlanServiceI {

    private final MealPlanRepository mealPlanRepository;
    private final MealPlanMapper mealPlanMapper;

    public MealPlanService(MealPlanRepository mealPlanRepository, MealPlanMapper mealPlanMapper) {
        this.mealPlanRepository = mealPlanRepository;
        this.mealPlanMapper = mealPlanMapper;
    }

    @Override
    public MealPlanDto addMealPlanEntry(MealPlanDto mealPlanDto) {
        System.out.println("***********");

        MealPlanEntity mealPlanEntity = mealPlanMapper.toMealPlanEntity(mealPlanDto);
        MealPlanEntity savedItem = mealPlanRepository.save(mealPlanEntity);
        MealPlanDto savedDto = mealPlanMapper.toMealPlanDto(savedItem);
        return savedDto;
    }

    @Override
    public List<MealPlanDto> getAllMealPlanEntries() {
        // db operations and send data
        List<MealPlanEntity> mealPlanEntityList = mealPlanRepository.findAll();
        List<MealPlanDto> mealPlanDtoList = mealPlanMapper.toMealPlanDtoList(mealPlanEntityList);
        return mealPlanDtoList;
    }

    @Override
    public boolean hasRequestedMealPlan(String username) {
        return mealPlanRepository.existsByUsername(username);
    }
}
