package com.bit.backend.services.impl;

import com.bit.backend.dtos.WorkoutTemplateDto;
import com.bit.backend.entities.WorkoutTemplateEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.WorkoutTemplateMapper;
import com.bit.backend.repositories.TemplateExerciseRepository;
import com.bit.backend.repositories.WorkoutTemplateRepository;
import com.bit.backend.services.WorkoutTemplateServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutTemplateService implements WorkoutTemplateServiceI {
    private final WorkoutTemplateRepository workoutTemplateRepository;
    private final WorkoutTemplateMapper workoutTemplateMapper;
    private final TemplateExerciseRepository templateExerciseRepository;

    public WorkoutTemplateService(WorkoutTemplateRepository workoutTemplateRepository, WorkoutTemplateMapper workoutTemplateMapper, TemplateExerciseRepository templateExerciseRepository) {
        this.workoutTemplateRepository = workoutTemplateRepository;
        this.workoutTemplateMapper = workoutTemplateMapper;
        this.templateExerciseRepository = templateExerciseRepository;
    }

    @Override
    public WorkoutTemplateDto createWorkoutTemplateEntity(WorkoutTemplateDto workoutTemplateDto) {
        try {
            System.out.println("************ In Service *************");

            WorkoutTemplateEntity workoutTemplateEntity = workoutTemplateMapper.toWorkoutTemplateEntity(workoutTemplateDto);
            WorkoutTemplateEntity savedItem = workoutTemplateRepository.save(workoutTemplateEntity);
            WorkoutTemplateDto savedWorkoutTemplateDto = workoutTemplateMapper.toWorkoutTemplateDto(savedItem);
            return savedWorkoutTemplateDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public WorkoutTemplateDto editWorkoutTemplate(long id, WorkoutTemplateDto workoutTemplateDto) {
        try {
            Optional<WorkoutTemplateEntity> optionalWorkoutTemplateEntity = workoutTemplateRepository.findById(id);

            if (!optionalWorkoutTemplateEntity.isPresent()) {
                throw new AppException("Workout Template Does Not Exist", HttpStatus.BAD_REQUEST);
            }

            WorkoutTemplateEntity newWorkoutTemplateEntity = workoutTemplateMapper.toWorkoutTemplateEntity(workoutTemplateDto);
            newWorkoutTemplateEntity.setId(id);
            WorkoutTemplateEntity workoutTemplateEntity = workoutTemplateRepository.save(newWorkoutTemplateEntity);
            WorkoutTemplateDto responseWorkoutTemplateDto = workoutTemplateMapper.toWorkoutTemplateDto(workoutTemplateEntity);
            return responseWorkoutTemplateDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<WorkoutTemplateDto> getAllWorkoutTemplates() {
        try {
            List<WorkoutTemplateEntity> entities = workoutTemplateRepository.findAll();
            List<WorkoutTemplateDto> dtos = workoutTemplateMapper.toWorkoutTemplateDto(entities);
            dtos.forEach(dto -> dto.setExerciseCount(
                    templateExerciseRepository.countByTemplateId(dto.getId())));
            return dtos;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public WorkoutTemplateDto getWorkoutTemplateById(long id) {
        try {
            WorkoutTemplateEntity entity = workoutTemplateRepository.findById(id)
                    .orElseThrow(() -> new AppException("Workout Template not found", HttpStatus.NOT_FOUND));
            WorkoutTemplateDto dto = workoutTemplateMapper.toWorkoutTemplateDto(entity);
            dto.setExerciseCount(templateExerciseRepository.countByTemplateId(dto.getId()));
            return dto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public WorkoutTemplateDto deleteWorkoutTemplate(long id) {
        try {
            WorkoutTemplateEntity existingWorkoutTemplate = workoutTemplateRepository.findById(id)
                    .orElseThrow(() -> new AppException("Workout Template does not exist", HttpStatus.BAD_REQUEST));

            // Soft delete by setting isDeleted flag to true
            existingWorkoutTemplate.setDeleted(true);

            WorkoutTemplateEntity updatedWorkoutTemplate = workoutTemplateRepository.save(existingWorkoutTemplate);

            return workoutTemplateMapper.toWorkoutTemplateDto(updatedWorkoutTemplate);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
