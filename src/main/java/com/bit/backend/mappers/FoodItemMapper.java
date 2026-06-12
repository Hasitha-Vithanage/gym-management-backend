package com.bit.backend.mappers;

import com.bit.backend.dtos.FoodItemDto;
import com.bit.backend.entities.FoodItemEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface FoodItemMapper {

    FoodItemDto toDto(FoodItemEntity entity);
    FoodItemEntity toEntity(FoodItemDto dto);
    List<FoodItemDto> toDtoList(List<FoodItemEntity> entities);
}
