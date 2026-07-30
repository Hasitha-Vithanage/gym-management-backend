package com.bit.backend.controllers;

import com.bit.backend.dtos.SupplierDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.SupplierServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class SupplierController {
    private SupplierServiceI supplierServiceI;

    public SupplierController(SupplierServiceI supplierServiceI) {
        this.supplierServiceI = supplierServiceI;
    }

    @PostMapping("/suppliers")
    public ResponseEntity<SupplierDto> addSupplier(@RequestBody SupplierDto supplierDto) {
        try {
            SupplierDto supplierDtoResponse = supplierServiceI.addSupplierEntity(supplierDto);
            return ResponseEntity.created(URI.create("/suppliers/" + supplierDtoResponse.getId())).body(supplierDtoResponse);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to add supplier. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-suppliers")
    public ResponseEntity<List<SupplierDto>> getSuppliers() {
        List<SupplierDto> supplierDtoList = supplierServiceI.getSuppliers();
        return ResponseEntity.ok().body(supplierDtoList);
    }

    @GetMapping("supplement/get-suppliers")
    public ResponseEntity<List<SupplierDto>> getSupplementSuppliers() {
        List<SupplierDto> supplierDtoList = supplierServiceI.getSupplementSuppliers();
        return ResponseEntity.ok().body(supplierDtoList);
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDto>> getAllSupplier() {
        List<SupplierDto> supplierDtoList = supplierServiceI.getSupplier();
        return ResponseEntity.ok().body(supplierDtoList);
    }

    @PutMapping("/suppliers/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable long id, @RequestBody SupplierDto supplierDto) {
        try {
            SupplierDto supplierDtoResponse = supplierServiceI.updateSupplier(id, supplierDto);
            return ResponseEntity.ok().body(supplierDtoResponse);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to update supplier. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<SupplierDto> deleteSupplier(@PathVariable long id) {
        try {
            SupplierDto supplierDto = supplierServiceI.deleteSupplier(id);
            return ResponseEntity.ok().body(supplierDto);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to delete supplier. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/supplier-count")
    public ResponseEntity<Long> getSupplierCount() {
        Long supplierCount = supplierServiceI.getSupplierCount();
        return ResponseEntity.ok().body(supplierCount);
    }
}
