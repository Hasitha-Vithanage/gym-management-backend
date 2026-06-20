package com.bit.backend.mappers;

import com.bit.backend.dtos.SupplementProductDto;
import com.bit.backend.entities.SupplementProductEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SupplementProductMapper {

    SupplementProductDto toDto(SupplementProductEntity entity);
    SupplementProductEntity toEntity(SupplementProductDto dto);
    List<SupplementProductDto> toDtoList(List<SupplementProductEntity> entities);
}
