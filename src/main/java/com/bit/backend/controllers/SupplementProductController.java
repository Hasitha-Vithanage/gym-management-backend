package com.bit.backend.controllers;

import com.bit.backend.dtos.SupplementProductDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.SupplementProductServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/supplements")
public class SupplementProductController {

    private final SupplementProductServiceI service;

    public SupplementProductController(SupplementProductServiceI service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SupplementProductDto> createProduct(@RequestBody SupplementProductDto dto) {
        try {
            SupplementProductDto response = service.createProduct(dto);
            return ResponseEntity.created(URI.create("/supplements/" + response.getId())).body(response);
        } catch (Exception e) {
            throw new AppException("Failed to create product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<SupplementProductDto>> getProductsForMembers() {
        try {
            return ResponseEntity.ok(service.getAllProductsForMembers());
        } catch (Exception e) {
            throw new AppException("Failed to load products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SupplementProductDto>> getProductsForStaff() {
        try {
            return ResponseEntity.ok(service.getAllProductsForStaff());
        } catch (Exception e) {
            throw new AppException("Failed to load products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplementProductDto> updateProduct(@PathVariable Long id, @RequestBody SupplementProductDto dto) {
        try {
            return ResponseEntity.ok(service.updateProduct(id, dto));
        } catch (Exception e) {
            throw new AppException("Failed to update product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<SupplementProductDto> deleteProduct(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.deleteProduct(id));
        } catch (Exception e) {
            throw new AppException("Failed to delete product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
