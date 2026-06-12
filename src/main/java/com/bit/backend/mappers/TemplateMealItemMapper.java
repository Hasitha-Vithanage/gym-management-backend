package com.bit.backend.mappers;

import com.bit.backend.dtos.TemplateMealItemDto;
import com.bit.backend.entities.TemplateMealItemEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TemplateMealItemMapper {

    TemplateMealItemDto toDto(TemplateMealItemEntity entity);
    TemplateMealItemEntity toEntity(TemplateMealItemDto dto);
    List<TemplateMealItemDto> toDtoList(List<TemplateMealItemEntity> entities);
}
