package com.bit.backend.services.impl;

import com.bit.backend.dtos.WorkoutPlanRequestDto;
import com.bit.backend.entities.WorkoutPlanRequestEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.WorkoutPlanRequestMapper;
import com.bit.backend.repositories.WorkoutPlanRequestRepository;
import com.bit.backend.services.WorkoutPlanRequestServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutPlanRequestService implements WorkoutPlanRequestServiceI {

    private final WorkoutPlanRequestRepository workoutPlanRequestRepository;
    private final WorkoutPlanRequestMapper workoutPlanRequestMapper;

    public WorkoutPlanRequestService(WorkoutPlanRequestRepository workoutPlanRequestRepository, WorkoutPlanRequestMapper workoutPlanRequestMapper) {
        this.workoutPlanRequestRepository = workoutPlanRequestRepository;
        this.workoutPlanRequestMapper = workoutPlanRequestMapper;
    }

    // addEmployeeEntity method
    @Override
    public WorkoutPlanRequestDto addWorkoutPlanRequestEntity(WorkoutPlanRequestDto workoutPlanRequestDto) {
        try {
            System.out.println("************ In Service *************");

            WorkoutPlanRequestEntity workoutPlanRequestEntity = workoutPlanRequestMapper.toWorkoutPlanRequestEntity(workoutPlanRequestDto);
            WorkoutPlanRequestEntity savedItem = workoutPlanRequestRepository.save(workoutPlanRequestEntity);
            WorkoutPlanRequestDto savedWorkoutPlanRequestDto = workoutPlanRequestMapper.toWorkoutPlanRequestDto(savedItem);
            return savedWorkoutPlanRequestDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // getEmployee method
    @Override
    public List<WorkoutPlanRequestDto> getWorkoutPlanRequest() {
        try {
            // db operations and send data
            List<WorkoutPlanRequestEntity> workoutPlanRequestEntityList = workoutPlanRequestRepository.findAll();
            List<WorkoutPlanRequestDto> workoutPlanRequestDtoList = workoutPlanRequestMapper.toWorkoutPlanRequestDto(workoutPlanRequestEntityList);
            return workoutPlanRequestDtoList;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

