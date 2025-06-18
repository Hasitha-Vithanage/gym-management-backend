package com.bit.backend.services.impl;

import com.bit.backend.dtos.SupplementInventoryDto;
import com.bit.backend.entities.SupplementInventoryEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.SupplementInventoryMapper;
import com.bit.backend.repositories.SupplementInventoryRepository;
import com.bit.backend.services.SupplementInventoryServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplementInventoryService implements SupplementInventoryServiceI {

    private final SupplementInventoryRepository supplementInventoryRepository;
    private final SupplementInventoryMapper supplementInventoryMapper;

    public SupplementInventoryService(SupplementInventoryRepository supplementInventoryRepository, SupplementInventoryMapper supplementInventoryMapper) {
        this.supplementInventoryRepository = supplementInventoryRepository;
        this.supplementInventoryMapper = supplementInventoryMapper;
    }

    @Override
    public SupplementInventoryDto addSupplementInventoryEntity(SupplementInventoryDto supplementInventoryDto) {
        System.out.println("In the addSupplementInventoryEntity method");

        SupplementInventoryEntity supplementInventoryEntity = supplementInventoryMapper.toSupplementInventoryEntity(supplementInventoryDto);
        SupplementInventoryEntity savedItem = supplementInventoryRepository.save(supplementInventoryEntity);
        SupplementInventoryDto savedDto = supplementInventoryMapper.toSupplementInventoryDto(savedItem);
        return savedDto;
    }

    @Override
    public List<SupplementInventoryDto> getSupplementInventory() {
        List<SupplementInventoryEntity> supplementInventoryEntities = supplementInventoryRepository.findAll();
        List<SupplementInventoryDto> supplementInventoryDtoList = supplementInventoryMapper.toSupplementInventoryDto(supplementInventoryEntities);
        return supplementInventoryDtoList;
    }

    @Override
    public SupplementInventoryDto updateSupplementInventory(long id, SupplementInventoryDto supplementInventoryDto) {
        System.out.println("In the updateSupplementInventoryEntity method");

        Optional<SupplementInventoryEntity> optionalSupplementInventoryEntity = supplementInventoryRepository.findById(id);

        if (!optionalSupplementInventoryEntity.isPresent()) {
            throw new AppException("SupplementInventory Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        SupplementInventoryEntity newSupplementInventoryEntity = supplementInventoryMapper.toSupplementInventoryEntity(supplementInventoryDto);
        newSupplementInventoryEntity.setId(id);
        SupplementInventoryEntity savedItem = supplementInventoryRepository.save(newSupplementInventoryEntity);
        SupplementInventoryDto savedDto = supplementInventoryMapper.toSupplementInventoryDto(savedItem);
        return savedDto;
    }

    @Override
    public SupplementInventoryDto deleteSupplementInventory(long id) {
        Optional<SupplementInventoryEntity> optionalSupplementInventoryEntity = supplementInventoryRepository.findById(id);
        if (!optionalSupplementInventoryEntity.isPresent()) {
            throw new AppException("SupplementInventory Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        supplementInventoryRepository.deleteById(id);
        SupplementInventoryDto deletedDto = supplementInventoryMapper.toSupplementInventoryDto(optionalSupplementInventoryEntity.get());
        return deletedDto;
    }
}
