package com.bit.backend.controllers;

import com.bit.backend.dtos.MaintenanceLogDto;
import com.bit.backend.services.MaintenanceLogServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaintenanceLogController {

    private final MaintenanceLogServiceI maintenanceLogServiceI;

    public MaintenanceLogController(MaintenanceLogServiceI maintenanceLogServiceI) {
        this.maintenanceLogServiceI = maintenanceLogServiceI;
    }

    @GetMapping("/equipment-maintenance/{equipmentId}")
    public ResponseEntity<List<MaintenanceLogDto>> getLogs(@PathVariable Long equipmentId) {
        return ResponseEntity.ok(maintenanceLogServiceI.getLogsByEquipment(equipmentId));
    }

    @PostMapping("/equipment-maintenance/{equipmentId}")
    public ResponseEntity<MaintenanceLogDto> addLog(@PathVariable Long equipmentId,
                                                     @RequestBody MaintenanceLogDto dto) {
        return ResponseEntity.ok(maintenanceLogServiceI.addLog(equipmentId, dto));
    }
}
