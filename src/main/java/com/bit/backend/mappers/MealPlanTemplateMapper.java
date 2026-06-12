package com.bit.backend.mappers;

import com.bit.backend.dtos.MealPlanTemplateDto;
import com.bit.backend.entities.MealPlanTemplateEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MealPlanTemplateMapper {

    MealPlanTemplateDto toDto(MealPlanTemplateEntity entity);
    MealPlanTemplateEntity toEntity(MealPlanTemplateDto dto);
    List<MealPlanTemplateDto> toDtoList(List<MealPlanTemplateEntity> entities);
}
