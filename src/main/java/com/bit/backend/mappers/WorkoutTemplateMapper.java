package com.bit.backend.mappers;

import com.bit.backend.dtos.WorkoutTemplateDto;
import com.bit.backend.entities.WorkoutTemplateEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface WorkoutTemplateMapper {

    WorkoutTemplateDto toWorkoutTemplateDto(WorkoutTemplateEntity workoutTemplateEntity);
    WorkoutTemplateEntity toWorkoutTemplateEntity(WorkoutTemplateDto workoutTemplateDto);
    List<WorkoutTemplateDto> toWorkoutTemplateDto(List<WorkoutTemplateEntity> workoutTemplateEntities);
}
