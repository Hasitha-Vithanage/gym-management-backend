package com.bit.backend.services.impl;

import com.bit.backend.dtos.EquipmentDto;
import com.bit.backend.entities.EquipmentEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.EquipmentMapper;
import com.bit.backend.repositories.EquipmentRepository;
import com.bit.backend.services.EquipmentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EquipmentService implements EquipmentServiceI {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    public EquipmentService(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public EquipmentDto addEquipmentEntity(EquipmentDto equipmentDto) {
        System.out.println("In the addEquipmentEntity method");

        EquipmentEntity equipmentEntity = equipmentMapper.toEquipmentEntity(equipmentDto);
        EquipmentEntity savedItem = equipmentRepository.save(equipmentEntity);
        EquipmentDto savedDto = equipmentMapper.toEquipmentDto(savedItem);
        return savedDto;
    }

    @Override
    public List<EquipmentDto> getEquipment() {
        List<EquipmentEntity> equipmentEntities = equipmentRepository.findAll();
        List<EquipmentDto> equipmentDtoList = equipmentMapper.toEquipmentDtoList(equipmentEntities);
        return equipmentDtoList;
    }

    @Override
    public EquipmentDto updateEquipment(long id, EquipmentDto equipmentDto) {
        System.out.println("In the updateEquipmentEntity method");

        Optional<EquipmentEntity> optionalEquipmentEntity = equipmentRepository.findById(id);

        if (!optionalEquipmentEntity.isPresent()) {
            throw new AppException("Equipment Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        EquipmentEntity newEquipmentEntity = equipmentMapper.toEquipmentEntity(equipmentDto);
        newEquipmentEntity.setId(id);
        EquipmentEntity savedItem = equipmentRepository.save(newEquipmentEntity);
        EquipmentDto savedDto = equipmentMapper.toEquipmentDto(savedItem);
        return savedDto;
    }

    @Override
    public EquipmentDto deleteEquipment(long id) {
        Optional<EquipmentEntity> optionalEquipmentEntity = equipmentRepository.findById(id);
        if (!optionalEquipmentEntity.isPresent()) {
            throw new AppException("Equipment Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        equipmentRepository.deleteById(id);
        EquipmentDto deletedDto = equipmentMapper.toEquipmentDto(optionalEquipmentEntity.get());
        return deletedDto;
    }

    @Override
    public EquipmentDto updateStatus(long id, String status) {
        EquipmentEntity entity = equipmentRepository.findById(id)
                .orElseThrow(() -> new AppException("Equipment Does Not Exist", HttpStatus.BAD_REQUEST));
        entity.setStatus(status);
        return equipmentMapper.toEquipmentDto(equipmentRepository.save(entity));
    }

    @Override
    public Map<String, Long> getStatusSummary() {
        Map<String, Long> summary = new HashMap<>();
        summary.put("total", equipmentRepository.count());
        summary.put("active", equipmentRepository.countByStatus("ACTIVE"));
        summary.put("maintenance", equipmentRepository.countByStatus("MAINTENANCE"));
        summary.put("outOfService", equipmentRepository.countByStatus("OUT_OF_SERVICE"));
        summary.put("overdue", (long) equipmentRepository.findOverdue().size());
        return summary;
    }

    @Override
    public List<EquipmentDto> getOverdueEquipments() {
        return equipmentMapper.toEquipmentDtoList(equipmentRepository.findOverdue());
    }
}
