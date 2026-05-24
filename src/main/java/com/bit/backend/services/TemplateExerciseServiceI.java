package com.bit.backend.services;

import com.bit.backend.dtos.TemplateExerciseDto;

import java.util.List;

public interface TemplateExerciseServiceI {
    List<TemplateExerciseDto> getExercisesByTemplateId(Long templateId);
    List<TemplateExerciseDto> saveAllExercises(Long templateId, List<TemplateExerciseDto> exercises);
    void deleteExercise(Long id);
}
