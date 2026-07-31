package com.bit.backend.services.impl;

import com.bit.backend.dtos.MemberNutritionProfileDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.MemberNutritionProfileEntity;
import com.bit.backend.entities.User;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MemberNutritionProfileMapper;
import com.bit.backend.repositories.AssignTrainerRepository;
import com.bit.backend.repositories.MemberNutritionProfileRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.MemberNutritionProfileServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MemberNutritionProfileService implements MemberNutritionProfileServiceI {

    private final MemberNutritionProfileRepository repository;
    private final MemberNutritionProfileMapper mapper;
    private final UserRepository userRepository;
    private final AssignTrainerRepository assignTrainerRepository;

    public MemberNutritionProfileService(MemberNutritionProfileRepository repository,
                                         MemberNutritionProfileMapper mapper,
                                         UserRepository userRepository,
                                         AssignTrainerRepository assignTrainerRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.assignTrainerRepository = assignTrainerRepository;
    }

    @Override
    public MemberNutritionProfileDto addProfile(MemberNutritionProfileDto dto) {
        MemberNutritionProfileEntity entity = mapper.toEntity(dto);
        MemberNutritionProfileEntity saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public List<MemberNutritionProfileDto> getAllProfiles() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public boolean hasProfile(String userId) {
        return repository.existsByUserId(userId);
    }

    @Override
    public MemberNutritionProfileDto getProfileByUserId(String userId) {
        MemberNutritionProfileEntity entity = repository.findTopByUserIdOrderByIdDesc(userId)
                .orElseThrow(() -> new AppException("Nutrition profile not found for user: " + userId, HttpStatus.NOT_FOUND));
        return mapper.toDto(entity);
    }

    @Override
    public List<MemberNutritionProfileDto> getPendingRequestsForTrainer(Long trainerUserId) {
        User trainerUser = userRepository.findById(trainerUserId)
                .orElseThrow(() -> new AppException("Trainer not found", HttpStatus.NOT_FOUND));

        Long employeeId = trainerUser.getEmployeeLoginId();
        if (employeeId == null) return Collections.emptyList();

        List<AssignTrainerEntity> assignments = assignTrainerRepository.findByTrainerId(employeeId);
        if (assignments.isEmpty()) return Collections.emptyList();

        List<Long> memberTableIds = assignments.stream()
                .map(AssignTrainerEntity::getMemberId)
                .collect(Collectors.toList());

        List<User> memberUsers = userRepository.findByCustomerLoginIdIn(memberTableIds);
        if (memberUsers.isEmpty()) return Collections.emptyList();

        Map<String, Long> loginToUserId = memberUsers.stream()
                .collect(Collectors.toMap(User::getLogin, User::getId, (a, b) -> a));

        List<MemberNutritionProfileDto> dtos = mapper.toDtoList(
                repository.findByStatusAndUserIdIn("Pending", new ArrayList<>(loginToUserId.keySet())));

        dtos.forEach(d -> d.setMemberUserId(loginToUserId.get(d.getUserId())));
        return dtos;
    }

    @Override
    public MemberNutritionProfileDto updateStatusByUserId(String userId, String status) {
        return repository.findTopByUserIdOrderByIdDesc(userId)
                .map(entity -> {
                    entity.setStatus(status);
                    return mapper.toDto(repository.save(entity));
                })
                .orElse(null);
    }
}
