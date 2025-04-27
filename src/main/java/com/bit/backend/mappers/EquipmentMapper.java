package com.bit.backend.mappers;

import com.bit.backend.dtos.EquipmentDto;
import com.bit.backend.entities.EquipmentEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EquipmentMapper {
    EquipmentDto toEquipmentDto(EquipmentEntity equipmentEntity);
    EquipmentEntity toEquipmentEntity(EquipmentDto equipmentDto);
    List<EquipmentDto> toEquipmentDtoList(List<EquipmentEntity> equipmentEntities);
}
