package com.bit.backend.services.impl;

import com.bit.backend.dtos.SupplierDto;
import com.bit.backend.entities.SupplierEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.SupplierMapper;
import com.bit.backend.repositories.SupplierRepository;
import com.bit.backend.services.SupplierServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements SupplierServiceI {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public SupplierDto addSupplierEntity(SupplierDto supplierDto) {
        System.out.println("In the addSupplierEntity method");

        SupplierEntity supplierEntity = supplierMapper.toSupplierEntity(supplierDto);
        SupplierEntity savedItem = supplierRepository.save(supplierEntity);
        SupplierDto savedDto = supplierMapper.toSupplierDto(savedItem);
        return savedDto;
    }

    @Override
    public List<SupplierDto> getSupplier() {
        List<SupplierEntity> supplierEntities = supplierRepository.findAll();
        List<SupplierDto> supplierDtoList = supplierMapper.toSupplierDto(supplierEntities);
        return supplierDtoList;
    }

    @Override
    public SupplierDto updateSupplier(long id, SupplierDto supplierDto) {
        System.out.println("In the updateSupplierEntity method");

        Optional<SupplierEntity> optionalSupplierEntity = supplierRepository.findById(id);

        if (!optionalSupplierEntity.isPresent()) {
            throw new AppException("Supplier Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        SupplierEntity newSupplierEntity = supplierMapper.toSupplierEntity(supplierDto);
        newSupplierEntity.setId(id);
        SupplierEntity savedItem = supplierRepository.save(newSupplierEntity);
        SupplierDto savedDto = supplierMapper.toSupplierDto(savedItem);
        return savedDto;
    }

    @Override
    public SupplierDto deleteSupplier(long id) {
        Optional<SupplierEntity> optionalSupplierEntity = supplierRepository.findById(id);
        if (!optionalSupplierEntity.isPresent()) {
            throw new AppException("Supplier Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        supplierRepository.deleteById(id);
        SupplierDto deletedDto = supplierMapper.toSupplierDto(optionalSupplierEntity.get());
        return deletedDto;
    }

}
