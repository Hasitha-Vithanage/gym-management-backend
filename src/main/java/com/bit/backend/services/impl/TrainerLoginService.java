package com.bit.backend.services.impl;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.TrainerLoginDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.TrainerLoginEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AssignTrainerMapper;
import com.bit.backend.mappers.TrainerLoginMapper;
import com.bit.backend.repositories.AssignTrainerRepository;
import com.bit.backend.repositories.TrainerLoginRepository;
import com.bit.backend.services.TrainerLoginServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TrainerLoginService implements TrainerLoginServiceI {

    private final TrainerLoginRepository trainerLoginRepository;
    private final TrainerLoginMapper trainerLoginMapper;

    public TrainerLoginService(TrainerLoginRepository trainerLoginRepository, TrainerLoginMapper trainerLoginMapper) {
        this.trainerLoginRepository = trainerLoginRepository;
        this.trainerLoginMapper = trainerLoginMapper;
    }

    // addEmployeeEntity method
    @Override
    public TrainerLoginDto addTrainerLoginEntity(TrainerLoginDto trainerLoginDto) {
        try {
            System.out.println("************ In Service *************");

            TrainerLoginEntity trainerLoginEntity = trainerLoginMapper.toTrainerLoginEntity(trainerLoginDto);
            TrainerLoginEntity savedItem = trainerLoginRepository.save(trainerLoginEntity);
            TrainerLoginDto savedTrainerLoginDto = trainerLoginMapper.toTrainerLoginDto(savedItem);
            return savedTrainerLoginDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
