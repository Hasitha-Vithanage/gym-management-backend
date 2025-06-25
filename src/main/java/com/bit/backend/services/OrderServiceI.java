package com.bit.backend.services;

import com.bit.backend.dtos.OrderDto;

import java.util.List;

public interface OrderServiceI {

    // Add a new order (with order items and billing details)
    OrderDto addOrder(OrderDto orderDto);
}
