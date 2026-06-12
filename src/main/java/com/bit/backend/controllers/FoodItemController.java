package com.bit.backend.controllers;

import com.bit.backend.dtos.FoodItemDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.FoodItemServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/food-items")
public class FoodItemController {

    private final FoodItemServiceI service;

    public FoodItemController(FoodItemServiceI service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FoodItemDto> createFoodItem(@RequestBody FoodItemDto dto) {
        try {
            FoodItemDto response = service.createFoodItem(dto);
            return ResponseEntity.created(URI.create("/food-items/" + response.getId())).body(response);
        } catch (Exception e) {
            throw new AppException("Failed to create food item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<FoodItemDto>> getAllFoodItems() {
        try {
            return ResponseEntity.ok(service.getAllFoodItems());
        } catch (Exception e) {
            throw new AppException("Failed to load food items: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItemDto> updateFoodItem(@PathVariable Long id, @RequestBody FoodItemDto dto) {
        try {
            return ResponseEntity.ok(service.updateFoodItem(id, dto));
        } catch (Exception e) {
            throw new AppException("Failed to update food item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<FoodItemDto> deleteFoodItem(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.deleteFoodItem(id));
        } catch (Exception e) {
            throw new AppException("Failed to delete food item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
