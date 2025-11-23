package com.bit.backend.mappers;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.WorkoutPlanDto;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.entities.WorkoutPlanEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface WorkoutPlanMapper {

    WorkoutPlanDto toWorkoutPlanDto(WorkoutPlanEntity workoutPlanEntity);
    WorkoutPlanEntity toWorkoutPlanEntity(WorkoutPlanDto workoutPlanDto);
    List<WorkoutPlanDto> toWorkoutPlanDto(List<WorkoutPlanEntity> workoutPlanEntities);
}
