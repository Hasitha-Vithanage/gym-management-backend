package com.bit.backend.services.impl;

import com.bit.backend.dtos.ProgressTrackingDto;
import com.bit.backend.entities.ProgressTrackingEntity;
import com.bit.backend.mappers.ProgressTrackingMapper;
import com.bit.backend.repositories.ProgressTrackingRepository;
import com.bit.backend.services.ProgressTrackingServiceI;
import org.springframework.stereotype.Service;

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
}
