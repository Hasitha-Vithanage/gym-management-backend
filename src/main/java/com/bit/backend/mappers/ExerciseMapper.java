package com.bit.backend.mappers;

import com.bit.backend.dtos.ExerciseDto;
import com.bit.backend.entities.ExerciseEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ExerciseMapper {

    ExerciseDto toExerciseDto(ExerciseEntity exerciseEntity);
    ExerciseEntity toExerciseEntity(ExerciseDto exerciseDto);
    List<ExerciseDto> toExerciseDto(List<ExerciseEntity> exerciseEntities);
}
