package com.bit.backend.services.impl;

import com.bit.backend.dtos.SupplierDto;
import com.bit.backend.entities.SupplierEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.SupplierMapper;
import com.bit.backend.repositories.EquipmentRepository;
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
    private final EquipmentRepository equipmentRepository;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper,
                            EquipmentRepository equipmentRepository) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public SupplierDto addSupplierEntity(SupplierDto supplierDto) {
        System.out.println("In the addSupplierEntity method");

        validateSupplier(supplierDto);

        SupplierEntity supplierEntity = supplierMapper.toSupplierEntity(supplierDto);
        SupplierEntity savedItem = supplierRepository.save(supplierEntity);
        SupplierDto savedDto = supplierMapper.toSupplierDto(savedItem);
        return savedDto;
    }

    private void validateSupplier(SupplierDto dto) {
        if (dto.getSupplierName() == null || dto.getSupplierName().isBlank()) {
            throw new AppException("Supplier name is required.", HttpStatus.BAD_REQUEST);
        }
        if (dto.getContactPerson() == null || dto.getContactPerson().isBlank()) {
            throw new AppException("Contact person is required.", HttpStatus.BAD_REQUEST);
        }
        if (dto.getContactNo() == null || dto.getContactNo().isBlank()) {
            throw new AppException("Contact number is required.", HttpStatus.BAD_REQUEST);
        }
        if (dto.getEmailAddress() == null || dto.getEmailAddress().isBlank()) {
            throw new AppException("Email address is required.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<SupplierDto> getSupplier() {
        List<SupplierEntity> supplierEntities = supplierRepository.findAllActive();
        List<SupplierDto> supplierDtoList = supplierMapper.toSupplierDtoList(supplierEntities);
        return supplierDtoList;
    }

    @Override
    public List<SupplierDto> getSuppliers() {
        // call repository interface to get data
        List<SupplierEntity> supplierEntities = supplierRepository.getSuppliers();
        List<SupplierDto> supplierDtoLsit = supplierMapper.toSupplierDtoList(supplierEntities);
        return supplierDtoLsit;
    }

    @Override
    public List<SupplierDto> getSupplementSuppliers() {
        // call repository interface to get data
        List<SupplierEntity> supplierEntities = supplierRepository.getSupplementSuppliers();
        List<SupplierDto> supplierDtoLsit = supplierMapper.toSupplierDtoList(supplierEntities);
        return supplierDtoLsit;
    }

    @Override
    public SupplierDto updateSupplier(long id, SupplierDto supplierDto) {
        System.out.println("In the updateSupplierEntity method");

        Optional<SupplierEntity> optionalSupplierEntity = supplierRepository.findById(id);

        if (!optionalSupplierEntity.isPresent()) {
            throw new AppException("Supplier Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        validateSupplier(supplierDto);

        SupplierEntity newSupplierEntity = supplierMapper.toSupplierEntity(supplierDto);
        newSupplierEntity.setId(id);
        SupplierEntity savedItem = supplierRepository.save(newSupplierEntity);
        SupplierDto savedDto = supplierMapper.toSupplierDto(savedItem);
        return savedDto;
    }

    @Override
    public SupplierDto deleteSupplier(long id) {
        SupplierEntity supplierEntity = supplierRepository.findById(id)
                .orElseThrow(() -> new AppException("Supplier Does Not Exist", HttpStatus.BAD_REQUEST));

        long linkedEquipmentCount = equipmentRepository.countBySupplier(id);
        if (linkedEquipmentCount > 0) {
            throw new AppException(
                "This supplier is linked to " + linkedEquipmentCount + " equipment item(s). " +
                "Please reassign those equipment records to a different supplier before deleting.",
                HttpStatus.CONFLICT);
        }

        supplierEntity.setDeleted(true);
        SupplierEntity savedItem = supplierRepository.save(supplierEntity);
        return supplierMapper.toSupplierDto(savedItem);
    }

    @Override
    public Long getSupplierCount() {
        return (long) supplierRepository.findAllActive().size();
    }
}
