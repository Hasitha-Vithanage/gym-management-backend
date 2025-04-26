package com.bit.backend.controllers;

import com.bit.backend.dtos.SupplierDto;
import com.bit.backend.services.SupplierServiceI;
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
        SupplierDto supplierDtoResponse = supplierServiceI.addSupplierEntity(supplierDto);
        return ResponseEntity.created(URI.create("/suppliers" + supplierDtoResponse.getSupplierName())).body(supplierDtoResponse);
    }

    @GetMapping("/equipments/get-suppliers")
    public ResponseEntity<List<SupplierDto>> getSuppliers() {
        List<SupplierDto> supplierDtoList = supplierServiceI.getSuppliers();
        return ResponseEntity.ok().body(supplierDtoList);
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDto>> getAllSupplier() {
        List<SupplierDto> supplierDtoList = supplierServiceI.getSupplier();
        return ResponseEntity.ok().body(supplierDtoList);
    }

    @PutMapping("/suppliers/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable long id, @RequestBody SupplierDto supplierDto) {
        SupplierDto supplierDtoResponse = supplierServiceI.updateSupplier(id, supplierDto);
        return ResponseEntity.ok().body(supplierDtoResponse);
    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<SupplierDto> deleteSupplier(@PathVariable long id) {
        SupplierDto supplierDto = supplierServiceI.deleteSupplier(id);
        return ResponseEntity.ok().body(supplierDto);
    }

}
