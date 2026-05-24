package com.bit.backend.mappers;

import com.bit.backend.dtos.TemplateExerciseDto;
import com.bit.backend.entities.TemplateExerciseEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TemplateExerciseMapper {

    TemplateExerciseDto toDto(TemplateExerciseEntity entity);
    TemplateExerciseEntity toEntity(TemplateExerciseDto dto);
    List<TemplateExerciseDto> toDtoList(List<TemplateExerciseEntity> entities);
}
