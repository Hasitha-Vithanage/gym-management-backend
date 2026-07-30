package com.bit.backend.services.impl;

import com.bit.backend.dtos.ExerciseDto;
import com.bit.backend.entities.ExerciseEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ExerciseMapper;
import com.bit.backend.repositories.ExerciseRepository;
import com.bit.backend.services.ExerciseServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService implements ExerciseServiceI {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public ExerciseDto createExerciseEntity(ExerciseDto exerciseDto) {
        try {
            System.out.println("************ In Service *************");

            if (exerciseDto.getExerciseName() != null && !exerciseDto.getExerciseName().isBlank()
                    && exerciseRepository.existsByExerciseNameIgnoreCaseAndIsDeletedFalse(exerciseDto.getExerciseName())) {
                throw new AppException("An exercise with this name already exists.", HttpStatus.CONFLICT);
            }

            ExerciseEntity exerciseEntity = exerciseMapper.toExerciseEntity(exerciseDto);
            ExerciseEntity savedItem = exerciseRepository.save(exerciseEntity);
            ExerciseDto savedExerciseDto = exerciseMapper.toExerciseDto(savedItem);
            return savedExerciseDto;
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ExerciseDto editExercise(long id, ExerciseDto exerciseDto) {
        try {
            Optional<ExerciseEntity> optionalExerciseEntity = exerciseRepository.findById(id);

            if (!optionalExerciseEntity.isPresent()) {
                throw new AppException("Exercise Does Not Exist", HttpStatus.BAD_REQUEST);
            }

            if (exerciseDto.getExerciseName() != null && !exerciseDto.getExerciseName().isBlank()
                    && exerciseRepository.existsByExerciseNameIgnoreCaseAndIsDeletedFalseAndIdNot(exerciseDto.getExerciseName(), id)) {
                throw new AppException("An exercise with this name already exists.", HttpStatus.CONFLICT);
            }

            ExerciseEntity newExerciseEntity = exerciseMapper.toExerciseEntity(exerciseDto);
            newExerciseEntity.setId(id);
            ExerciseEntity exerciseEntity = exerciseRepository.save(newExerciseEntity);
            ExerciseDto responseExerciseDto = exerciseMapper.toExerciseDto(exerciseEntity);
            return responseExerciseDto;
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // getEmployee method
    @Override
    public List<ExerciseDto> getAllExercises() {
        try {
            // db operations and send data
            List<ExerciseEntity> exerciseEntityList = exerciseRepository.findAll();
            List<ExerciseDto> exerciseDtoList = exerciseMapper.toExerciseDto(exerciseEntityList);
            return exerciseDtoList;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ExerciseDto deleteExercise(long id) {
        try {
            ExerciseEntity existingExercise = exerciseRepository.findById(id)
                    .orElseThrow(() -> new AppException("Exercise does not exist", HttpStatus.BAD_REQUEST));

            // Soft delete by setting isDeleted flag to true
            existingExercise.setDeleted(true);

            ExerciseEntity updatedExercise = exerciseRepository.save(existingExercise);

            return exerciseMapper.toExerciseDto(updatedExercise);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
