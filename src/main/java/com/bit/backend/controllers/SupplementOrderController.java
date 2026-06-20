package com.bit.backend.controllers;

import com.bit.backend.dtos.SupplementOrderDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.SupplementOrderServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/supplement-orders")
public class SupplementOrderController {

    private final SupplementOrderServiceI service;

    public SupplementOrderController(SupplementOrderServiceI service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SupplementOrderDto> placeOrder(@RequestBody SupplementOrderDto dto) {
        try {
            SupplementOrderDto response = service.placeOrder(dto);
            return ResponseEntity.created(URI.create("/supplement-orders/" + response.getId())).body(response);
        } catch (Exception e) {
            throw new AppException("Failed to place order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<SupplementOrderDto>> getAllOrders() {
        try {
            return ResponseEntity.ok(service.getAllOrders());
        } catch (Exception e) {
            throw new AppException("Failed to load orders: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/member/{username}")
    public ResponseEntity<List<SupplementOrderDto>> getOrdersByMember(@PathVariable String username) {
        try {
            return ResponseEntity.ok(service.getOrdersByMember(username));
        } catch (Exception e) {
            throw new AppException("Failed to load orders: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<SupplementOrderDto> completeOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.completeOrder(id));
        } catch (Exception e) {
            throw new AppException("Failed to complete order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<SupplementOrderDto> cancelOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.cancelOrder(id));
        } catch (Exception e) {
            throw new AppException("Failed to cancel order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
