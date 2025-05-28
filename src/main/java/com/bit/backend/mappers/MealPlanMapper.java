package com.bit.backend.mappers;

import com.bit.backend.dtos.MealPlanDto;
import com.bit.backend.entities.MealPlanEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MealPlanMapper {

    MealPlanDto toMealPlanDto(MealPlanEntity mealPlanEntity);
    MealPlanEntity toMealPlanEntity(MealPlanDto mealPlanDto);
    List<MealPlanDto> toMealPlanDtoList(List<MealPlanEntity> mealPlanEntityList);
}
