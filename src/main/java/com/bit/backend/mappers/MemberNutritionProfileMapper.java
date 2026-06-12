package com.bit.backend.mappers;

import com.bit.backend.dtos.MemberNutritionProfileDto;
import com.bit.backend.entities.MemberNutritionProfileEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MemberNutritionProfileMapper {

    MemberNutritionProfileDto toDto(MemberNutritionProfileEntity entity);
    MemberNutritionProfileEntity toEntity(MemberNutritionProfileDto dto);
    List<MemberNutritionProfileDto> toDtoList(List<MemberNutritionProfileEntity> entities);
}
