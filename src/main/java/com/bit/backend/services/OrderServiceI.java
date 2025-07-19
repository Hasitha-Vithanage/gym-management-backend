package com.bit.backend.services;

import com.bit.backend.dtos.OrderDto;
import com.bit.backend.dtos.PaymentsDto;

import java.util.List;

public interface OrderServiceI {

    // Add a new order (with order items and billing details)
    OrderDto addOrder(OrderDto orderDto);
    List<OrderDto> getOrders();
    List<OrderDto> getOrdersOverLimit();
    OrderDto.OrderItemDto getOrderItemById(Long id);
    OrderDto getOrderDetailById(Long id);
    OrderDto updateOrderStatus(Long id);
    OrderDto.BillingDetailsDto getBillingDetailsById(Long id);
}
