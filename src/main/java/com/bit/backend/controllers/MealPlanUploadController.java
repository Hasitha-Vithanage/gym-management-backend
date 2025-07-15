package com.bit.backend.controllers;

import com.bit.backend.dtos.MealPlanUploadDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.MealPlanServiceI;
import com.bit.backend.services.MealPlanUploadServiceI;
import com.bit.backend.services.impl.MealPlanService;
import com.bit.backend.services.impl.MealPlanUploadService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
public class MealPlanUploadController {

    private final MealPlanUploadServiceI mealPlanUploadServiceI;
    private final MealPlanServiceI mealPlanServiceI;

    public MealPlanUploadController(MealPlanUploadServiceI mealPlanUploadServiceI, MealPlanServiceI mealPlanServiceI) {
        this.mealPlanUploadServiceI = mealPlanUploadServiceI;
        this.mealPlanServiceI = mealPlanServiceI;
    }

    @PostMapping(value = "/meal-plan-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MealPlanUploadDto> addMealPlanUpload(
            @RequestPart("mealPlanTitle") String title,
            @RequestPart("planDescription") String description,
            @RequestPart("userId") String userId,
            @RequestPart("pdf") MultipartFile file) {

        try {
            MealPlanUploadDto dto = new MealPlanUploadDto();
            dto.setMealPlanTitle(title);
            dto.setPlanDescription(description);
            dto.setUserId(userId); // 👈 this line ensures userId is captured
            dto.setPdf(file.getBytes());
            dto.setPdfName(file.getOriginalFilename());
            dto.setPdfType(file.getContentType());

            MealPlanUploadDto response = mealPlanUploadServiceI.addMealPlanUploadEntity(dto);
//            mealPlanServiceI.updateStatus(userId);

            return ResponseEntity.created(URI.create("/meal-plan-upload/" + response.getId())).body(response);
        } catch (Exception e) {
            throw new AppException("Upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/my-meal-plan/{userId}")
    public ResponseEntity<byte[]> getMealPlanUploadPdf(@PathVariable String userId) {
        MealPlanUploadDto dto = mealPlanUploadServiceI.getMealPlanUploadByUserId(userId); // You need this method
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + dto.getPdfName() + "\"")
                .body(dto.getPdf());
    }

}
