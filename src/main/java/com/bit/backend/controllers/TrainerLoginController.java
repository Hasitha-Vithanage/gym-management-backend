package com.bit.backend.controllers;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.TrainerLoginDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.AssignTrainerServiceI;
import com.bit.backend.services.TrainerLoginServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class TrainerLoginController {

    private final TrainerLoginServiceI trainerLoginServiceI;

    public TrainerLoginController(TrainerLoginServiceI trainerLoginServiceI) {
        this.trainerLoginServiceI = trainerLoginServiceI;
    }

    @PostMapping("/trainer-login")
    public ResponseEntity<TrainerLoginDto> addTrainerLogin(@RequestBody TrainerLoginDto trainerLoginDto) {
        try {
            TrainerLoginDto trainerLoginDtoResponse = trainerLoginServiceI.addTrainerLoginEntity(trainerLoginDto);
            return ResponseEntity.created(URI.create("/trainer-login" + trainerLoginDtoResponse.getId())).body(trainerLoginDtoResponse);
        } catch (Exception e) {
            throw new AppException("Assign Login failed. Please try again later. " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/trainer-login")
    public ResponseEntity<List<TrainerLoginDto>> getTrainerLogin() {
        // calling service through interface

        List<TrainerLoginDto> trainerLoginDtoList = trainerLoginServiceI.getTrainerLoginEntity();
        return ResponseEntity.ok(trainerLoginDtoList);
    }
}
