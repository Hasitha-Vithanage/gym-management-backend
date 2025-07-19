package com.bit.backend.services.impl;

import com.bit.backend.dtos.EquipmentDto;
import com.bit.backend.dtos.OrderDto;
import com.bit.backend.dtos.PaymentsDto;
import com.bit.backend.dtos.SupplementInventoryDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.OrderMapper;
import com.bit.backend.repositories.BillingDetailsRepository;
import com.bit.backend.repositories.OrderItemRepository;
import com.bit.backend.repositories.OrderRepository;
import com.bit.backend.repositories.SupplementInventoryRepository;
import com.bit.backend.services.OrderServiceI;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceI {


    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final BillingDetailsRepository billingDetailsRepository;
    private final OrderMapper orderMapper;
    private final SupplementInventoryRepository supplementInventoryRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        BillingDetailsRepository billingDetailsRepository,
                        OrderMapper orderMapper, SupplementInventoryRepository supplementInventoryRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.billingDetailsRepository = billingDetailsRepository;
        this.orderMapper = orderMapper;
        this.supplementInventoryRepository = supplementInventoryRepository;
    }

    @Override
    @Transactional
    public OrderDto addOrder(OrderDto orderDto) {
        System.out.println("In the addOrder method");

        // Step 1: Save the OrderEntity first
        OrderEntity orderEntity = orderMapper.toOrderEntity(orderDto);
        orderEntity.setStatus("Unpaid");
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        // Step 2: Process Order Items and update stock
        List<OrderItemEntity> orderItems = new ArrayList<>();
        if (orderDto.getOrderItems() != null) {
            for (OrderDto.OrderItemDto itemDto : orderDto.getOrderItems()) {

                // Get the product from supplement inventory
                Optional<SupplementInventoryEntity> supplementOpt =
                        supplementInventoryRepository.findById(itemDto.getProductId());

                if (supplementOpt.isPresent()) {
                    SupplementInventoryEntity supplement = supplementOpt.get();

                    // Check if enough stock is available
                    if (supplement.getQuantityInStock() < itemDto.getQuantity()) {
                        throw new RuntimeException("Insufficient stock for product: " + supplement.getProductName());
                    }

                    // Deduct the ordered quantity from stock
                    supplement.setQuantityInStock(supplement.getQuantityInStock() - itemDto.getQuantity());
                    supplementInventoryRepository.save(supplement);

                    // Map and save order item
                    OrderItemEntity itemEntity = orderMapper.toOrderItemEntity(itemDto);
                    itemEntity.setOrder(savedOrder); // set parent order reference
                    orderItems.add(orderItemRepository.save(itemEntity));

                } else {
                    throw new RuntimeException("Supplement product not found with ID: " + itemDto.getProductId());
                }
            }
        }

        // Step 3: Save BillingDetails if present
        BillingDetailsEntity billingEntity = null;
        if (orderDto.getBillingDetails() != null) {
            billingEntity = orderMapper.toBillingDetailsEntity(orderDto.getBillingDetails());
            billingEntity.setOrder(savedOrder);
            billingDetailsRepository.save(billingEntity);
        }

        // Step 4: Return the mapped DTO
        OrderDto savedDto = orderMapper.toOrderDto(savedOrder);
        savedDto.setOrderItems(orderMapper.toOrderItemDtoList(orderItems));
        if (billingEntity != null) {
            savedDto.setBillingDetails(orderMapper.toBillingDetailsDto(billingEntity));
        }

        return savedDto;
    }

    @Override
    public List<OrderDto> getOrders() {
        try {
            // db operations and send data
            List<OrderEntity> orderEntityList = orderRepository.findAll();
            List<OrderDto> orderDtoList = orderMapper.toOrderDtoList(orderEntityList);
            return orderDtoList;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<OrderDto> getOrdersOverLimit() {
        List<OrderEntity> orderEntityList = orderRepository.getOrdersOverLimit();
        List<OrderDto> orderDtoList = orderMapper.toOrderDtoList(orderEntityList);
        return orderDtoList;
    }

    @Override
    public OrderDto.OrderItemDto getOrderItemById(Long id) {
//        OrderItemEntity orderItemEntity = orderItemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order Item not found with ID: " + id));
//        return orderMapper.toOrderItemDto(orderItemEntity);

        OrderItemEntity orderItemEntity = orderItemRepository.findByOrderId(id);

        if (orderItemEntity == null) {
            throw new AppException("Order Item not found with ID: " + id, HttpStatus.BAD_REQUEST);
        }

        return orderMapper.toOrderItemDto(orderItemEntity);
    }

    @Override
    public OrderDto getOrderDetailById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        return orderMapper.toOrderDto(orderEntity);

    }

    @Override
    public OrderDto updateOrderStatus(Long id) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(id);

        if (!optionalOrderEntity.isPresent()) {
            throw new AppException("Order Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        optionalOrderEntity.get().setStatus("Paid");
        return orderMapper.toOrderDto(orderRepository.save(optionalOrderEntity.get()));

//        EquipmentEntity newEquipmentEntity = equipmentMapper.toEquipmentEntity(equipmentDto);
//        newEquipmentEntity.setId(id);
//        EquipmentEntity savedItem = equipmentRepository.save(newEquipmentEntity);
//        EquipmentDto savedDto = equipmentMapper.toEquipmentDto(savedItem);
    }

    @Override
    public OrderDto.BillingDetailsDto getBillingDetailsById(Long id) {
        BillingDetailsEntity billingDetailsEntity = billingDetailsRepository.findByOrderId(id);

        if (billingDetailsEntity == null) {
            throw new AppException("Billing Details not found with ID: " + id, HttpStatus.BAD_REQUEST);
        }

        return orderMapper.toBillingDetailsDto(billingDetailsEntity);
    }

}
