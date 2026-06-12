package com.bit.backend.controllers;

import com.bit.backend.dtos.MealPlanTemplateDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.MealPlanTemplateServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/meal-plan-templates")
public class MealPlanTemplateController {

    private final MealPlanTemplateServiceI service;

    public MealPlanTemplateController(MealPlanTemplateServiceI service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MealPlanTemplateDto> createTemplate(@RequestBody MealPlanTemplateDto dto) {
        try {
            MealPlanTemplateDto response = service.createTemplate(dto);
            return ResponseEntity.created(URI.create("/meal-plan-templates/" + response.getId())).body(response);
        } catch (Exception e) {
            throw new AppException("Failed to create meal plan template: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<MealPlanTemplateDto>> getAllTemplates() {
        try {
            return ResponseEntity.ok(service.getAllTemplates());
        } catch (Exception e) {
            throw new AppException("Failed to load meal plan templates: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealPlanTemplateDto> getTemplateById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getTemplateById(id));
        } catch (Exception e) {
            throw new AppException("Failed to load meal plan template: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealPlanTemplateDto> updateTemplate(@PathVariable Long id, @RequestBody MealPlanTemplateDto dto) {
        try {
            return ResponseEntity.ok(service.updateTemplate(id, dto));
        } catch (Exception e) {
            throw new AppException("Failed to update meal plan template: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<MealPlanTemplateDto> deleteTemplate(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.deleteTemplate(id));
        } catch (Exception e) {
            throw new AppException("Failed to delete meal plan template: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
