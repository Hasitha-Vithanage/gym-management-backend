package com.bit.backend.controllers;

import com.bit.backend.dtos.EquipmentDto;
import com.bit.backend.services.EquipmentServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class EquipmentController {
    private EquipmentServiceI equipmentServiceI;

    public EquipmentController(EquipmentServiceI equipmentServiceI) {
        this.equipmentServiceI = equipmentServiceI;
    }

    @PostMapping("/equipments")
    public ResponseEntity<EquipmentDto> addEquipment(@RequestBody EquipmentDto equipmentDto) {
        EquipmentDto equipmentDtoResponse = equipmentServiceI.addEquipmentEntity(equipmentDto);
        return ResponseEntity.created(URI.create("/equipments/" + equipmentDtoResponse.getId())).body(equipmentDtoResponse);
    }

    @GetMapping("/equipments")
    public ResponseEntity<List<EquipmentDto>> getAllEquipments() {
        List<EquipmentDto> equipmentDtoList = equipmentServiceI.getEquipment();
        return ResponseEntity.ok().body(equipmentDtoList);
    }

    @PutMapping("/equipments/{id}")
    public ResponseEntity<EquipmentDto> updateEquipment(@PathVariable long id, @RequestBody EquipmentDto equipmentDto) {
        EquipmentDto equipmentDtoResponse = equipmentServiceI.updateEquipment(id, equipmentDto);
        return ResponseEntity.ok().body(equipmentDtoResponse);
    }

    @DeleteMapping("/equipments/{id}")
    public ResponseEntity<EquipmentDto> deleteEquipment(@PathVariable long id) {
        EquipmentDto equipmentDto = equipmentServiceI.deleteEquipment(id);
        return ResponseEntity.ok().body(equipmentDto);
    }

    @PatchMapping("/equipments/{id}/status")
    public ResponseEntity<EquipmentDto> updateStatus(@PathVariable long id, @RequestBody Map<String, String> body) {
        EquipmentDto updated = equipmentServiceI.updateStatus(id, body.get("status"));
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/equipments/summary")
    public ResponseEntity<Map<String, Long>> getStatusSummary() {
        return ResponseEntity.ok(equipmentServiceI.getStatusSummary());
    }

    @GetMapping("/equipments/overdue")
    public ResponseEntity<List<EquipmentDto>> getOverdueEquipments() {
        return ResponseEntity.ok(equipmentServiceI.getOverdueEquipments());
    }
}
