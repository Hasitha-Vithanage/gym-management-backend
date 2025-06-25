package com.bit.backend.services.impl;

import com.bit.backend.dtos.OrderDto;
import com.bit.backend.entities.BillingDetailsEntity;
import com.bit.backend.entities.OrderEntity;
import com.bit.backend.entities.OrderItemEntity;
import com.bit.backend.mappers.OrderMapper;
import com.bit.backend.repositories.BillingDetailsRepository;
import com.bit.backend.repositories.OrderItemRepository;
import com.bit.backend.repositories.OrderRepository;
import com.bit.backend.services.OrderServiceI;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements OrderServiceI {


    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final BillingDetailsRepository billingDetailsRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        BillingDetailsRepository billingDetailsRepository,
                        OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.billingDetailsRepository = billingDetailsRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderDto addOrder(OrderDto orderDto) {
        System.out.println("In the addOrder method");

        // Map and save OrderEntity
        OrderEntity orderEntity = orderMapper.toOrderEntity(orderDto);
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        // Map and save OrderItemEntities
        List<OrderItemEntity> orderItems = new ArrayList<>();
        if (orderDto.getOrderItems() != null) {
            for (OrderDto.OrderItemDto itemDto : orderDto.getOrderItems()) {
                OrderItemEntity itemEntity = orderMapper.toOrderItemEntity(itemDto);
                itemEntity.setOrder(savedOrder);
                orderItems.add(orderItemRepository.save(itemEntity));
            }
        }

        // Map and save BillingDetailsEntity
        BillingDetailsEntity billingEntity = null;
        if (orderDto.getBillingDetails() != null) {
            billingEntity = orderMapper.toBillingDetailsEntity(orderDto.getBillingDetails());
            billingEntity.setOrder(savedOrder);
            billingDetailsRepository.save(billingEntity);
        }

        // Map saved entities back to DTO
        OrderDto savedDto = orderMapper.toOrderDto(savedOrder);
        savedDto.setOrderItems(orderMapper.toOrderItemDtoList(orderItems));
        if (billingEntity != null) {
            savedDto.setBillingDetails(orderMapper.toBillingDetailsDto(billingEntity));
        }

        return savedDto;
    }

}
