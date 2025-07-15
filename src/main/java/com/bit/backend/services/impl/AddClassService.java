package com.bit.backend.services.impl;

import com.bit.backend.dtos.AddClassDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.SupplementInventoryDto;
import com.bit.backend.entities.AddClassEntity;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.SupplementInventoryEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AddClassMapper;
import com.bit.backend.repositories.AddClassRepository;
import com.bit.backend.services.AddClassServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public AddClassDto updateAddClass(long id, AddClassDto addClassDto) {
        try {
            Optional<AddClassEntity> optionalAddClassEntity = addClassRepository.findById(id);

            if (!optionalAddClassEntity.isPresent()) {
                throw new AppException("Class Does Not Exist", HttpStatus.BAD_REQUEST);
            }

            AddClassEntity newAddClassEntity = addClassMapper.toAddClassEntity(addClassDto);
            newAddClassEntity.setId(id);
            AddClassEntity addClassEntity = addClassRepository.save(newAddClassEntity);
            AddClassDto responseAddClassDto = addClassMapper.toAddClassDto(addClassEntity);
            return responseAddClassDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AddClassDto getClassById(long id) {
        Optional<AddClassEntity> optionalAddClassEntity = addClassRepository.findById(id);

        AddClassEntity entity = optionalAddClassEntity
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + id));

        AddClassDto addClassDto = addClassMapper.toAddClassDto(entity);

        return addClassDto;
    }
}
