package com.bit.backend.mappers;

import com.bit.backend.dtos.ProgressTrackingDto;
import com.bit.backend.entities.ProgressTrackingEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProgressTrackingMapper {

    ProgressTrackingDto toProgressTrackingDto(ProgressTrackingEntity progressTrackingEntity);
    ProgressTrackingEntity toProgressTrackingEntity(ProgressTrackingDto progressTrackingDto);
    List<ProgressTrackingDto> toProgressTrackingDto(List<ProgressTrackingEntity> progressTrackingEntities);
}
