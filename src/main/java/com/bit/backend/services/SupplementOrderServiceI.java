package com.bit.backend.services;

import com.bit.backend.dtos.SupplementOrderDto;

import java.util.List;

public interface SupplementOrderServiceI {

    SupplementOrderDto placeOrder(SupplementOrderDto dto);
    List<SupplementOrderDto> getAllOrders();
    List<SupplementOrderDto> getOrdersByMember(String memberUsername);
    SupplementOrderDto completeOrder(Long id);
    SupplementOrderDto cancelOrder(Long id);
}
