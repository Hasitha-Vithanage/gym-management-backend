package com.bit.backend.mappers;

import com.bit.backend.dtos.WorkoutTemplateDto;
import com.bit.backend.entities.WorkoutTemplateEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface WorkoutTemplateMapper {

    @Mapping(target = "ageRange", expression = "java(toAgeRange(entity.getMinAge(), entity.getMaxAge()))")
    @Mapping(target = "exerciseCount", ignore = true)
    WorkoutTemplateDto toWorkoutTemplateDto(WorkoutTemplateEntity entity);

    @Mapping(target = "minAge", expression = "java(dto.getAgeRange() != null ? dto.getAgeRange().getMin() : null)")
    @Mapping(target = "maxAge", expression = "java(dto.getAgeRange() != null ? dto.getAgeRange().getMax() : null)")
    WorkoutTemplateEntity toWorkoutTemplateEntity(WorkoutTemplateDto dto);

    List<WorkoutTemplateDto> toWorkoutTemplateDto(List<WorkoutTemplateEntity> entities);

    default WorkoutTemplateDto.AgeRange toAgeRange(Integer min, Integer max) {
        if (min == null && max == null) return null;
        return new WorkoutTemplateDto.AgeRange(min, max);
    }
}
