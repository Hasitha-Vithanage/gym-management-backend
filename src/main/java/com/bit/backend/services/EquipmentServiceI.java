package com.bit.backend.services;

import com.bit.backend.dtos.EquipmentDto;

import java.util.List;
import java.util.Map;

public interface EquipmentServiceI {
    EquipmentDto addEquipmentEntity(EquipmentDto equipmentDto);
    EquipmentDto updateEquipment(long id, EquipmentDto equipmentDto);
    List<EquipmentDto> getEquipment();
    EquipmentDto deleteEquipment(long id);
    EquipmentDto updateStatus(long id, String status);
    Map<String, Long> getStatusSummary();
    List<EquipmentDto> getOverdueEquipments();
}
