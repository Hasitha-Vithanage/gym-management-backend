package com.bit.backend.services;

import com.bit.backend.dtos.ExerciseDto;

import java.util.List;

public interface ExerciseServiceI {
    ExerciseDto createExerciseEntity(ExerciseDto exerciseDto);
    List<ExerciseDto> getAllExercises();
    ExerciseDto editExercise(long id, ExerciseDto ExerciseDto);
    ExerciseDto deleteExercise(long id);
}
