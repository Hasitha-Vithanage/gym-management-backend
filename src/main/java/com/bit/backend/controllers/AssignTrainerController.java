package com.bit.backend.controllers;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.OrderDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.AssignTrainerServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class AssignTrainerController {

    private final AssignTrainerServiceI assignTrainerServiceI;

    public AssignTrainerController(AssignTrainerServiceI assignTrainerServiceI) {
        this.assignTrainerServiceI = assignTrainerServiceI;
    }

    @PostMapping("/assign-trainer")
    public ResponseEntity<AssignTrainerDto> addAssignTrainer(@RequestBody AssignTrainerDto assignTrainerDto) {
        try {
            AssignTrainerDto assignTrainerDtoResponse = assignTrainerServiceI.addAssignTrainerEntity(assignTrainerDto);
            return ResponseEntity.created(URI.create("/assign-trainer" + assignTrainerDtoResponse.getMember())).body(assignTrainerDtoResponse);
        } catch (Exception e) {
            throw new AppException("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/assign-trainer")
    public ResponseEntity<List<AssignTrainerDto>> getAssignTrainer() {
        try {
            List<AssignTrainerDto> assignTrainerDtoList = assignTrainerServiceI.getAssignTrainer();
            return ResponseEntity.ok(assignTrainerDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to load AssignTrainer records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("assign-trainer/{id}")
    public ResponseEntity<AssignTrainerDto> updateAssignTrainer(@PathVariable long id, @RequestBody AssignTrainerDto assignTrainerDto) {
        try {
            AssignTrainerDto assignTrainerDtoResponse = assignTrainerServiceI.updateAssignTrainer(id, assignTrainerDto);
            return ResponseEntity.ok(assignTrainerDtoResponse);
        } catch (Exception e) {
            throw new AppException("Failed to update the AssignTrainer information. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("assign-trainer/{id}")
    public ResponseEntity<AssignTrainerDto> deleteAssignTrainer(@PathVariable long id) {
        try {
            AssignTrainerDto assignTrainerDto = assignTrainerServiceI.deleteAssignTrainer(id);
            return ResponseEntity.ok(assignTrainerDto);
        } catch (Exception e) {
            throw new AppException("Failed to delete the AssignTrainer record. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-assign-trainer/{memberName}")
    public ResponseEntity <AssignTrainerDto> getAssignTrainerByMember(@PathVariable String memberName) {
        try {
           AssignTrainerDto assignTrainerDto = assignTrainerServiceI.getAssignTrainerByMember(memberName);
            return ResponseEntity.ok(assignTrainerDto);
        } catch (Exception e) {
            throw new AppException("Failed to load AssignTrainer records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
