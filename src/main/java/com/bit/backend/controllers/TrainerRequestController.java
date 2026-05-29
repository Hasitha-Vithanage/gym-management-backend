package com.bit.backend.controllers;

import com.bit.backend.dtos.TrainerRequestDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.impl.TrainerRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainerRequestController {

    private final TrainerRequestService trainerRequestService;

    public TrainerRequestController(TrainerRequestService trainerRequestService) {
        this.trainerRequestService = trainerRequestService;
    }

    @PostMapping("/trainer-request")
    public ResponseEntity<TrainerRequestDto> createRequest(@RequestBody TrainerRequestDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(trainerRequestService.createRequest(dto));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to create trainer request: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/trainer-request/member/{memberId}")
    public ResponseEntity<TrainerRequestDto> getByMemberId(@PathVariable Long memberId) {
        try {
            TrainerRequestDto dto = trainerRequestService.getByMemberId(memberId);
            return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new AppException("Failed to get trainer request: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/trainer-request/member/{memberId}/status")
    public ResponseEntity<TrainerRequestDto> updateStatus(@PathVariable Long memberId, @RequestParam String status) {
        try {
            return ResponseEntity.ok(trainerRequestService.updateStatus(memberId, status));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to update trainer request status: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/trainer-request")
    public ResponseEntity<List<TrainerRequestDto>> getAllRequests() {
        try {
            return ResponseEntity.ok(trainerRequestService.getAllRequests());
        } catch (Exception e) {
            throw new AppException("Failed to get trainer requests: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
