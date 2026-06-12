package com.bit.backend.services.impl;

import com.bit.backend.dtos.MemberNutritionProfileDto;
import com.bit.backend.entities.MemberNutritionProfileEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MemberNutritionProfileMapper;
import com.bit.backend.repositories.MemberNutritionProfileRepository;
import com.bit.backend.services.MemberNutritionProfileServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberNutritionProfileService implements MemberNutritionProfileServiceI {

    private final MemberNutritionProfileRepository repository;
    private final MemberNutritionProfileMapper mapper;

    public MemberNutritionProfileService(MemberNutritionProfileRepository repository,
                                         MemberNutritionProfileMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
}
