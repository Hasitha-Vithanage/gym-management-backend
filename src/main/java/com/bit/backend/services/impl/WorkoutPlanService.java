package com.bit.backend.services.impl;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.WorkoutPlanDto;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.entities.WorkoutPlanEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.WorkoutPlanMapper;
import com.bit.backend.repositories.WorkoutPlanRepository;
import com.bit.backend.services.WorkoutPlanServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class WorkoutPlanService implements WorkoutPlanServiceI {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final WorkoutPlanMapper workoutPlanMapper;

    public WorkoutPlanService(WorkoutPlanRepository workoutPlanRepository, WorkoutPlanMapper workoutPlanMapper) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.workoutPlanMapper = workoutPlanMapper;
    }

    @Override
    public WorkoutPlanDto addWorkoutPlanEntity(WorkoutPlanDto workoutPlanDto) {
        System.out.println("In the addMemberEntity method");

        WorkoutPlanEntity workoutPlanEntity = workoutPlanMapper.toWorkoutPlanEntity(workoutPlanDto);
        WorkoutPlanEntity savedItem = workoutPlanRepository.save(workoutPlanEntity);
        WorkoutPlanDto savedDto = workoutPlanMapper.toWorkoutPlanDto(savedItem);
        return savedDto;
    }

    @Override
    public WorkoutPlanDto getWorkoutPlanByUserId(String userId) {
        try {
            WorkoutPlanEntity entity = workoutPlanRepository.findTopByUserIdOrderByIdDesc(userId)
                    .orElseThrow(() -> new AppException("Workout plan not found for user: " + userId, HttpStatus.NOT_FOUND));
            return workoutPlanMapper.toWorkoutPlanDto(entity);
        } catch (Exception e) {
            throw new AppException("Error fetching workout plan: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
