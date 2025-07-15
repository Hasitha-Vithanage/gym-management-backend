package com.bit.backend.services.impl;

import com.bit.backend.dtos.AddClassDto;
import com.bit.backend.entities.AddClassEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AddClassMapper;
import com.bit.backend.repositories.AddClassRepository;
import com.bit.backend.services.AddClassServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddClassService implements AddClassServiceI {

    private final AddClassRepository addClassRepository;
    private final AddClassMapper addClassMapper;

    public AddClassService(AddClassRepository addClassRepository, AddClassMapper addClassMapper) {
        this.addClassRepository = addClassRepository;
        this.addClassMapper = addClassMapper;
    }

    @Override
    public AddClassDto addAddClassEntity(AddClassDto addClassDto) {
    try {
        AddClassEntity addClassEntity = addClassMapper.toAddClassEntity(addClassDto);
        AddClassEntity savedEntity = addClassRepository.save(addClassEntity);
        AddClassDto responseAddClassDto = addClassMapper.toAddClassDto(savedEntity);
        return responseAddClassDto;
    }
    catch (Exception e) {
        throw new AppException("Request error with: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @Override
    public List<AddClassDto> getAddClass() {
    try{
        List<AddClassEntity> addClassEntityList = addClassRepository.findAll();
        List<AddClassDto> addClassDtoList = addClassMapper.toAddClassDto(addClassEntityList);
        return addClassDtoList;
    }
    catch (Exception e) {
        throw new AppException("Request error with: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}
