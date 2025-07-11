package com.bit.backend.mappers;

import com.bit.backend.dtos.TrainerLoginDto;
import com.bit.backend.dtos.WorkoutPlanRequestDto;
import com.bit.backend.entities.TrainerLoginEntity;
import com.bit.backend.entities.WorkoutPlanRequestEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface WorkoutPlanRequestMapper {

    WorkoutPlanRequestDto toWorkoutPlanRequestDto(WorkoutPlanRequestEntity workoutPlanRequestEntity);
    WorkoutPlanRequestEntity toWorkoutPlanRequestEntity(WorkoutPlanRequestDto workoutPlanRequestDto);
}
