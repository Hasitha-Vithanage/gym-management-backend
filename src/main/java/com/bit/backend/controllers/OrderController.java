package com.bit.backend.controllers;

import com.bit.backend.dtos.OrderDto;
import com.bit.backend.dtos.PaymentsDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.OrderServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {

    private final OrderServiceI orderServiceI;

    public OrderController(OrderServiceI orderServiceI) {
        this.orderServiceI = orderServiceI;
    }

    @PostMapping(value = "/supplement/place-order")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        try {
            OrderDto savedOrder = orderServiceI.addOrder(orderDto);
            return ResponseEntity.created(URI.create("/supplement/place-order" + savedOrder.getOrderId()))
                    .body(savedOrder);
        } catch (Exception e) {
            throw new AppException("Order creation failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-orders")
    public ResponseEntity<List<OrderDto>> getOrder() {
        try {
            List<OrderDto> orderDtoList = orderServiceI.getOrders();
            return ResponseEntity.ok(orderDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to load Orders records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-orders-over-limit")
    public ResponseEntity<List<OrderDto>> getOrdersOverLimit() {
        try {
            List<OrderDto> orderDtoList = orderServiceI.getOrdersOverLimit();
            return ResponseEntity.ok(orderDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to load Orders records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
