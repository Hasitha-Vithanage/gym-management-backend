package com.bit.backend.services;

import com.bit.backend.dtos.MaintenanceLogDto;

import java.util.List;

public interface MaintenanceLogServiceI {
    List<MaintenanceLogDto> getLogsByEquipment(Long equipmentId);
    MaintenanceLogDto addLog(Long equipmentId, MaintenanceLogDto dto);
}
