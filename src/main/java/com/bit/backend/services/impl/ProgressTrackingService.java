package com.bit.backend.services.impl;

import com.bit.backend.dtos.ProgressTrackingDto;
import com.bit.backend.entities.ProgressTrackingEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ProgressTrackingMapper;
import com.bit.backend.repositories.ProgressTrackingRepository;
import com.bit.backend.services.ProgressTrackingServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class ProgressTrackingService implements ProgressTrackingServiceI {

    private final ProgressTrackingRepository progressTrackingRepository;
    private final ProgressTrackingMapper progressTrackingMapper;

    public ProgressTrackingService(ProgressTrackingRepository progressTrackingRepository, ProgressTrackingMapper progressTrackingMapper) {
        this.progressTrackingRepository = progressTrackingRepository;
        this.progressTrackingMapper = progressTrackingMapper;
    }

    @Override
    public ProgressTrackingDto addProgressTrackingEntity(ProgressTrackingDto progressTrackingDto) {
        System.out.println("In the addProgressTrackingEntity method");

        ProgressTrackingEntity progressTrackingEntity = progressTrackingMapper.toProgressTrackingEntity(progressTrackingDto);
        ProgressTrackingEntity savedItem = progressTrackingRepository.save(progressTrackingEntity);
        ProgressTrackingDto savedDto = progressTrackingMapper.toProgressTrackingDto(savedItem);
        return savedDto;
    }

//    @Override
//    public List<Map<String, Object>> getWeightOverTime(String userName) {
//        return progressTrackingRepository.getWeightOverTime(userName);
//    }


    @Override
    public List<Map<String, Object>> getWeightOverTimeByUser(String userName) {
        return progressTrackingRepository.getWeightOverTimeByUser(userName);
    }

    @Override
    public List<ProgressTrackingDto> getProgressData() {
    try {
        List<ProgressTrackingEntity> progressTrackingEntities = progressTrackingRepository.findAll();
        List<ProgressTrackingDto> progressTrackingDtoList = progressTrackingMapper.toProgressTrackingDto(progressTrackingEntities);
        return progressTrackingDtoList;
    } catch (Exception e) {
        throw new AppException("Failed to load progress Data", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

}
