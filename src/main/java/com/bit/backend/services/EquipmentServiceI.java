package com.bit.backend.services;

import com.bit.backend.dtos.EquipmentDto;

import java.util.List;

public interface EquipmentServiceI {
    EquipmentDto addEquipmentEntity(EquipmentDto equipmentDto);
    EquipmentDto updateEquipment(long id, EquipmentDto equipmentDto);
    List<EquipmentDto> getEquipment();
    EquipmentDto deleteEquipment(long id);
}
