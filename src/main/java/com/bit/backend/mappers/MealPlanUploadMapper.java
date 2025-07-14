package com.bit.backend.mappers;

import com.bit.backend.dtos.MealPlanUploadDto;
import com.bit.backend.dtos.WorkoutPlanDto;
import com.bit.backend.entities.MealPlanUploadEntity;
import com.bit.backend.entities.WorkoutPlanEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MealPlanUploadMapper {

    MealPlanUploadDto toMealPlanUploadDto(MealPlanUploadEntity mealPlanUploadEntity);
    MealPlanUploadEntity toMealPlanUploadEntity(MealPlanUploadDto mealPlanUploadDto);
    List<MealPlanUploadDto> toMealPlanUploadDto(List<MealPlanUploadEntity> mealPlanUploadEntities);
}
