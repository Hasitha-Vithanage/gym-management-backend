package com.bit.backend.services;

import com.bit.backend.dtos.WorkoutTemplateDto;

import java.util.List;

public interface WorkoutTemplateServiceI {
    WorkoutTemplateDto createWorkoutTemplateEntity(WorkoutTemplateDto workoutTemplateDto);
    List<WorkoutTemplateDto> getAllWorkoutTemplates();
    WorkoutTemplateDto getWorkoutTemplateById(long id);
    WorkoutTemplateDto editWorkoutTemplate(long id, WorkoutTemplateDto workoutTemplateDto);
    WorkoutTemplateDto deleteWorkoutTemplate(long id);
}
