package com.bit.backend.mappers;

import com.bit.backend.dtos.SupplementInventoryDto;
import com.bit.backend.entities.SupplementInventoryEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SupplementInventoryMapper {
    SupplementInventoryDto toSupplementInventoryDto(SupplementInventoryEntity supplementInventoryEntity);
    SupplementInventoryEntity toSupplementInventoryEntity(SupplementInventoryDto supplementInventoryDto);
    List<SupplementInventoryDto> toSupplementInventoryDto(List<SupplementInventoryEntity> supplementInventoryEntities);
}
