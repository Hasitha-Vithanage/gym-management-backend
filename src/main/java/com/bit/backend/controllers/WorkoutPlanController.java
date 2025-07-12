package com.bit.backend.controllers;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.WorkoutPlanDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.MemberServiceI;
import com.bit.backend.services.WorkoutPlanServiceI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
public class WorkoutPlanController {

    private WorkoutPlanServiceI workoutPlanServiceI;

    public WorkoutPlanController(WorkoutPlanServiceI workoutPlanServiceI) {
        this.workoutPlanServiceI = workoutPlanServiceI;
    }

    @PostMapping(value = "/workout-plan-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WorkoutPlanDto> addWorkoutPlan(
            @RequestPart("workoutPlanTitle") String title,
            @RequestPart("planDescription") String description,
            @RequestPart("userId") String userId,
            @RequestPart("pdf") MultipartFile file) {

        try {
            WorkoutPlanDto dto = new WorkoutPlanDto();
            dto.setWorkoutPlanTitle(title);
            dto.setPlanDescription(description);
            dto.setUserId(userId); // 👈 this line ensures userId is captured
            dto.setPdf(file.getBytes());
            dto.setPdfName(file.getOriginalFilename());
            dto.setPdfType(file.getContentType());

            WorkoutPlanDto response = workoutPlanServiceI.addWorkoutPlanEntity(dto);
            return ResponseEntity.created(URI.create("/workout-plan-upload/" + response.getId())).body(response);
        } catch (Exception e) {
            throw new AppException("Upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/my-workout-plan/{userId}")
    public ResponseEntity<byte[]> getWorkoutPlanPdf(@PathVariable String userId) {
        WorkoutPlanDto dto = workoutPlanServiceI.getWorkoutPlanByUserId(userId); // You need this method
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + dto.getPdfName() + "\"")
                .body(dto.getPdf());
    }


}
