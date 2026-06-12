package com.bit.backend.controllers;

import com.bit.backend.dtos.TemplateMealItemDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.TemplateMealItemServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal-plan-templates")
public class TemplateMealItemController {

    private final TemplateMealItemServiceI service;

    public TemplateMealItemController(TemplateMealItemServiceI service) {
        this.service = service;
    }

    @GetMapping("/{templateId}/meal-items")
    public ResponseEntity<List<TemplateMealItemDto>> getMealItems(@PathVariable Long templateId) {
        try {
            return ResponseEntity.ok(service.getMealItemsByTemplateId(templateId));
        } catch (Exception e) {
            throw new AppException("Failed to load meal items: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{templateId}/meal-items")
    public ResponseEntity<List<TemplateMealItemDto>> saveMealItems(
            @PathVariable Long templateId,
            @RequestBody List<TemplateMealItemDto> items) {
        try {
            return ResponseEntity.ok(service.saveAllMealItems(templateId, items));
        } catch (Exception e) {
            throw new AppException("Failed to save meal items: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{templateId}/meal-items/{id}")
    public ResponseEntity<Void> deleteMealItem(@PathVariable Long templateId, @PathVariable Long id) {
        try {
            service.deleteMealItem(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new AppException("Failed to remove meal item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
