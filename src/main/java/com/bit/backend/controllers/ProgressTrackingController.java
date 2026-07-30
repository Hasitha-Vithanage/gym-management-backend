package com.bit.backend.controllers;

import com.bit.backend.dtos.ProgressTrackingDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.ProgressTrackingServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class ProgressTrackingController {

    private ProgressTrackingServiceI progressTrackingServiceI;

    public ProgressTrackingController(ProgressTrackingServiceI progressTrackingServiceI) {
        this.progressTrackingServiceI = progressTrackingServiceI;
    }

    @PostMapping("/progress-tracking/{userName}")
    public ResponseEntity<ProgressTrackingDto> addProgressTrackingEntity(@PathVariable String userName,
                                                                         @RequestBody ProgressTrackingDto progressTrackingDto) {
        try {
            progressTrackingDto.setUserName(userName);

            ProgressTrackingDto progressTrackingDtoResponse = progressTrackingServiceI.addProgressTrackingEntity(progressTrackingDto);
            return ResponseEntity.created(URI.create("/progress-tracking" + progressTrackingDtoResponse.getId())).body(progressTrackingDtoResponse);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/weight-over-time/{userName}")
    public ResponseEntity<List<Map<String, Object>>> getWeightOverTimeByUser(@PathVariable String userName) {
        try {
            List<Map<String, Object>> weightOverTime = progressTrackingServiceI.getWeightOverTimeByUser(userName);
            return ResponseEntity.ok(weightOverTime);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-progress-data")
    public ResponseEntity<List<ProgressTrackingDto>> getProgressData() {
        try {
            List<ProgressTrackingDto> progressData = progressTrackingServiceI.getProgressData();
            return ResponseEntity.ok(progressData);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
