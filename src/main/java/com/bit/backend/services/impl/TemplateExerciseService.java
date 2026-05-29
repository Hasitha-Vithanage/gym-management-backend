package com.bit.backend.services.impl;

import com.bit.backend.dtos.TemplateExerciseDto;
import com.bit.backend.entities.ExerciseEntity;
import com.bit.backend.entities.TemplateExerciseEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.TemplateExerciseMapper;
import com.bit.backend.repositories.ExerciseRepository;
import com.bit.backend.repositories.TemplateExerciseRepository;
import com.bit.backend.services.TemplateExerciseServiceI;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TemplateExerciseService implements TemplateExerciseServiceI {

    private final TemplateExerciseRepository templateExerciseRepository;
    private final TemplateExerciseMapper templateExerciseMapper;
    private final ExerciseRepository exerciseRepository;

    public TemplateExerciseService(TemplateExerciseRepository templateExerciseRepository,
                                   TemplateExerciseMapper templateExerciseMapper,
                                   ExerciseRepository exerciseRepository) {
        this.templateExerciseRepository = templateExerciseRepository;
        this.templateExerciseMapper = templateExerciseMapper;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<TemplateExerciseDto> getExercisesByTemplateId(Long templateId) {
        try {
            List<TemplateExerciseEntity> entities = templateExerciseRepository.findByTemplateId(templateId);
            List<TemplateExerciseDto> dtos = templateExerciseMapper.toDtoList(entities);

            // Batch-fetch exercise details and populate equipmentType
            List<Long> exerciseIds = entities.stream()
                    .map(TemplateExerciseEntity::getExerciseId)
                    .collect(Collectors.toList());

            Map<Long, String> equipmentMap = exerciseRepository.findAllById(exerciseIds)
                    .stream()
                    .collect(Collectors.toMap(ExerciseEntity::getId, ExerciseEntity::getEquipmentType));

            dtos.forEach(dto -> dto.setEquipmentType(equipmentMap.getOrDefault(dto.getExerciseId(), null)));

            return dtos;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public List<TemplateExerciseDto> saveAllExercises(Long templateId, List<TemplateExerciseDto> exercises) {
        try {
            templateExerciseRepository.deleteByTemplateId(templateId);
            List<TemplateExerciseEntity> entities = exercises.stream()
                    .map(dto -> {
                        dto.setTemplateId(templateId);
                        return templateExerciseMapper.toEntity(dto);
                    })
                    .toList();
            List<TemplateExerciseEntity> saved = templateExerciseRepository.saveAll(entities);
            return templateExerciseMapper.toDtoList(saved);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteExercise(Long id) {
        try {
            if (!templateExerciseRepository.existsById(id)) {
                throw new AppException("Exercise assignment not found", HttpStatus.NOT_FOUND);
            }
            templateExerciseRepository.deleteById(id);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
