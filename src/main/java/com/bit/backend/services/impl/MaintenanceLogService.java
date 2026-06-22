package com.bit.backend.services.impl;

import com.bit.backend.dtos.MaintenanceLogDto;
import com.bit.backend.entities.EquipmentEntity;
import com.bit.backend.entities.MaintenanceLogEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.repositories.EquipmentRepository;
import com.bit.backend.repositories.MaintenanceLogRepository;
import com.bit.backend.services.MaintenanceLogServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceLogService implements MaintenanceLogServiceI {

    private final MaintenanceLogRepository logRepository;
    private final EquipmentRepository equipmentRepository;

    public MaintenanceLogService(MaintenanceLogRepository logRepository, EquipmentRepository equipmentRepository) {
        this.logRepository = logRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public List<MaintenanceLogDto> getLogsByEquipment(Long equipmentId) {
        return logRepository.findByEquipmentIdOrderByPerformedDateDesc(equipmentId)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public MaintenanceLogDto addLog(Long equipmentId, MaintenanceLogDto dto) {
        EquipmentEntity equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new AppException("Equipment not found", HttpStatus.BAD_REQUEST));

        MaintenanceLogEntity entity = new MaintenanceLogEntity();
        entity.setEquipmentId(equipmentId);
        entity.setType(dto.getType());
        entity.setDescription(dto.getDescription());
        entity.setPerformedBy(dto.getPerformedBy());
        entity.setPerformedDate(dto.getPerformedDate());
        entity.setCost(dto.getCost());

        MaintenanceLogEntity saved = logRepository.save(entity);

        // Update equipment's lastMaintenance date
        equipment.setLastMaintenance(dto.getPerformedDate());
        if (dto.getPerformedDate() != null) {
            equipment.setNextMaintenance(dto.getPerformedDate().plusMonths(3));
        }
        equipmentRepository.save(equipment);

        return toDto(saved);
    }

    private MaintenanceLogDto toDto(MaintenanceLogEntity e) {
        MaintenanceLogDto dto = new MaintenanceLogDto();
        dto.setId(e.getId());
        dto.setEquipmentId(e.getEquipmentId());
        dto.setType(e.getType());
        dto.setDescription(e.getDescription());
        dto.setPerformedBy(e.getPerformedBy());
        dto.setPerformedDate(e.getPerformedDate());
        dto.setCost(e.getCost());
        return dto;
    }
}
