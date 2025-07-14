package com.bit.backend.services.impl;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.WorkoutPlanRequestDto;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.WorkoutPlanRequestEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.WorkoutPlanRequestMapper;
import com.bit.backend.repositories.WorkoutPlanRequestRepository;
import com.bit.backend.services.WorkoutPlanRequestServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public WorkoutPlanRequestDto deleteWorkoutPlanRequest(long id) {
        try {
            Optional<WorkoutPlanRequestEntity> optionalWorkoutPlanRequestEntity = workoutPlanRequestRepository.findById(id);

            if (!optionalWorkoutPlanRequestEntity.isPresent()) {
                throw new AppException("Workout Plan Request Does Not Exsist", HttpStatus.BAD_REQUEST);
            }

            workoutPlanRequestRepository.deleteById(id);

            WorkoutPlanRequestDto workoutPlanRequestDto = workoutPlanRequestMapper.toWorkoutPlanRequestDto(optionalWorkoutPlanRequestEntity.get());
            return workoutPlanRequestDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public WorkoutPlanRequestDto updateStatus(String userId) {
        try {
            WorkoutPlanRequestEntity entity = workoutPlanRequestRepository.findByUserId(userId)
                    .orElseThrow(() -> new AppException("Workout Plan Request does not exist for user: " + userId, HttpStatus.BAD_REQUEST));

            entity.setStatus("Uploaded");
            WorkoutPlanRequestEntity WorkoutPlanRequestEntity =  workoutPlanRequestRepository.save(entity);
            WorkoutPlanRequestDto responseWorkoutPlanRequestDto = workoutPlanRequestMapper.toWorkoutPlanRequestDto(WorkoutPlanRequestEntity);
            return responseWorkoutPlanRequestDto;

        } catch (Exception e) {
            throw new AppException("Failed to update status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

