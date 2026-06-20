package com.bit.backend.controllers;

import com.bit.backend.dtos.SupplementProductDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.SupplementProductServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/supplements")
public class SupplementProductController {

    private final SupplementProductServiceI service;

    public SupplementProductController(SupplementProductServiceI service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SupplementProductDto> createProduct(
            @RequestPart("productForm") SupplementProductDto dto,
            @RequestPart(value = "image", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                dto.setImage(file.getBytes());
                dto.setImageName(file.getOriginalFilename());
                dto.setImageType(file.getContentType());
            }
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

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SupplementProductDto> updateProduct(
            @PathVariable Long id,
            @RequestPart("productForm") SupplementProductDto dto,
            @RequestPart(value = "image", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                dto.setImage(file.getBytes());
                dto.setImageName(file.getOriginalFilename());
                dto.setImageType(file.getContentType());
            }
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
