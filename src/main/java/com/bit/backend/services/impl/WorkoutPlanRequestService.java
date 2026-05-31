package com.bit.backend.services.impl;

import com.bit.backend.dtos.WorkoutPlanRequestDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.User;
import com.bit.backend.entities.WorkoutPlanRequestEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.WorkoutPlanRequestMapper;
import com.bit.backend.repositories.AssignTrainerRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.repositories.WorkoutPlanRequestRepository;
import com.bit.backend.services.WorkoutPlanRequestServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutPlanRequestService implements WorkoutPlanRequestServiceI {

    private final WorkoutPlanRequestRepository workoutPlanRequestRepository;
    private final WorkoutPlanRequestMapper workoutPlanRequestMapper;
    private final UserRepository userRepository;
    private final AssignTrainerRepository assignTrainerRepository;

    public WorkoutPlanRequestService(WorkoutPlanRequestRepository workoutPlanRequestRepository,
                                     WorkoutPlanRequestMapper workoutPlanRequestMapper,
                                     UserRepository userRepository,
                                     AssignTrainerRepository assignTrainerRepository) {
        this.workoutPlanRequestRepository = workoutPlanRequestRepository;
        this.workoutPlanRequestMapper = workoutPlanRequestMapper;
        this.userRepository = userRepository;
        this.assignTrainerRepository = assignTrainerRepository;
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

    @Override
    public List<WorkoutPlanRequestDto> getRequestsByStatus(String status) {
        List<WorkoutPlanRequestEntity> entities = workoutPlanRequestRepository.findByStatus(status);
        return workoutPlanRequestMapper.toWorkoutPlanRequestDto(entities);
    }

    @Override
    public List<WorkoutPlanRequestDto> getPendingCustomRequestsForTrainer(Long trainerUserId) {
        // Get trainer's employee ID from their user account
        User trainerUser = userRepository.findById(trainerUserId)
                .orElseThrow(() -> new AppException("Trainer not found", HttpStatus.NOT_FOUND));

        Long employeeId = trainerUser.getEmployeeLoginId();
        if (employeeId == null) return Collections.emptyList();

        // Get all member IDs (member table IDs) assigned to this trainer
        List<AssignTrainerEntity> assignments = assignTrainerRepository.findByTrainerId(employeeId);
        if (assignments.isEmpty()) return Collections.emptyList();

        List<Long> memberTableIds = assignments.stream()
                .map(AssignTrainerEntity::getMemberId)
                .collect(Collectors.toList());

        // Resolve member table IDs → user IDs (user.customerLoginId == memberTableId)
        List<Long> memberUserIds = userRepository.findByCustomerLoginIdIn(memberTableIds)
                .stream().map(User::getId).collect(Collectors.toList());

        if (memberUserIds.isEmpty()) return Collections.emptyList();

        return workoutPlanRequestMapper.toWorkoutPlanRequestDto(
                workoutPlanRequestRepository.findByStatusAndMemberUserIdIn("Pending Custom", memberUserIds));
    }

    @Override
    public WorkoutPlanRequestDto getLastRequestByUserId(String userId) {
        return workoutPlanRequestRepository.findTopByUserIdOrderByIdDesc(userId)
                .map(workoutPlanRequestMapper::toWorkoutPlanRequestDto)
                .orElse(null);
    }

    @Override
    public WorkoutPlanRequestDto updateStatusByUserId(String userId, String status) {
        return workoutPlanRequestRepository.findTopByUserIdOrderByIdDesc(userId)
                .map(entity -> {
                    entity.setStatus(status);
                    return workoutPlanRequestMapper.toWorkoutPlanRequestDto(
                            workoutPlanRequestRepository.save(entity));
                })
                .orElse(null);
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
    public WorkoutPlanRequestDto updateStatus(long id) {
        try {
            WorkoutPlanRequestEntity entity = workoutPlanRequestRepository.findById(id)
                    .orElseThrow(() -> new AppException("Workout Plan Request does not exist for user: " + id, HttpStatus.BAD_REQUEST));

            entity.setStatus("Uploaded");
            WorkoutPlanRequestEntity WorkoutPlanRequestEntity =  workoutPlanRequestRepository.save(entity);
            WorkoutPlanRequestDto responseWorkoutPlanRequestDto = workoutPlanRequestMapper.toWorkoutPlanRequestDto(WorkoutPlanRequestEntity);
            return responseWorkoutPlanRequestDto;

        } catch (Exception e) {
            throw new AppException("Failed to update status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

