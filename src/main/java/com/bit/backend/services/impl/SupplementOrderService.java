package com.bit.backend.services.impl;

import com.bit.backend.dtos.SupplementOrderDto;
import com.bit.backend.dtos.SupplementOrderItemDto;
import com.bit.backend.entities.SupplementOrderEntity;
import com.bit.backend.entities.SupplementOrderItemEntity;
import com.bit.backend.entities.SupplementProductEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.SupplementOrderMapper;
import com.bit.backend.repositories.SupplementOrderRepository;
import com.bit.backend.repositories.SupplementProductRepository;
import com.bit.backend.services.SupplementOrderServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplementOrderService implements SupplementOrderServiceI {

    private final SupplementOrderRepository orderRepository;
    private final SupplementProductRepository productRepository;
    private final SupplementOrderMapper mapper;

    public SupplementOrderService(SupplementOrderRepository orderRepository,
                                  SupplementProductRepository productRepository,
                                  SupplementOrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public SupplementOrderDto placeOrder(SupplementOrderDto dto) {
        try {
            if (dto.getItems() == null || dto.getItems().isEmpty()) {
                throw new AppException("Order must contain at least one item.", HttpStatus.BAD_REQUEST);
            }

            SupplementOrderEntity order = new SupplementOrderEntity();
            order.setMemberUsername(dto.getMemberUsername());
            order.setStatus("PENDING");
            order.setOrderDate(LocalDateTime.now());
            order.setNotes(dto.getNotes());
            order.setIsDeleted(false);

            List<SupplementOrderItemEntity> items = new ArrayList<>();
            double total = 0.0;

            for (SupplementOrderItemDto itemDto : dto.getItems()) {
                SupplementProductEntity product = productRepository.findById(itemDto.getProductId())
                        .orElseThrow(() -> new AppException("Product not found: " + itemDto.getProductId(), HttpStatus.BAD_REQUEST));

                if (Boolean.TRUE.equals(product.getIsDeleted()) || Boolean.FALSE.equals(product.getIsActive())) {
                    throw new AppException("Product is not available: " + product.getProductName(), HttpStatus.BAD_REQUEST);
                }

                if (product.getStockQty() < itemDto.getQuantity()) {
                    throw new AppException("Insufficient stock for: " + product.getProductName(), HttpStatus.BAD_REQUEST);
                }

                product.setStockQty(product.getStockQty() - itemDto.getQuantity());
                productRepository.save(product);

                SupplementOrderItemEntity item = new SupplementOrderItemEntity();
                item.setOrder(order);
                item.setProduct(product);
                item.setQuantity(itemDto.getQuantity());
                item.setPriceAtOrder(product.getPrice());

                total += product.getPrice() * itemDto.getQuantity();
                items.add(item);
            }

            order.setItems(items);
            order.setTotalAmount(total);

            return mapper.toDto(orderRepository.save(order));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to place order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<SupplementOrderDto> getAllOrders() {
        try {
            return mapper.toDtoList(orderRepository.findAllByIsDeletedFalseOrderByOrderDateDesc());
        } catch (Exception e) {
            throw new AppException("Failed to load orders: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<SupplementOrderDto> getOrdersByMember(String memberUsername) {
        try {
            return mapper.toDtoList(
                    orderRepository.findAllByMemberUsernameAndIsDeletedFalseOrderByOrderDateDesc(memberUsername)
            );
        } catch (Exception e) {
            throw new AppException("Failed to load orders: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SupplementOrderDto completeOrder(Long id) {
        try {
            SupplementOrderEntity order = orderRepository.findById(id)
                    .orElseThrow(() -> new AppException("Order not found", HttpStatus.BAD_REQUEST));

            if (!"PENDING".equals(order.getStatus())) {
                throw new AppException("Only pending orders can be completed.", HttpStatus.BAD_REQUEST);
            }

            order.setStatus("COMPLETED");
            return mapper.toDto(orderRepository.save(order));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to complete order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public SupplementOrderDto cancelOrder(Long id) {
        try {
            SupplementOrderEntity order = orderRepository.findById(id)
                    .orElseThrow(() -> new AppException("Order not found", HttpStatus.BAD_REQUEST));

            if (!"PENDING".equals(order.getStatus())) {
                throw new AppException("Only pending orders can be cancelled.", HttpStatus.BAD_REQUEST);
            }

            for (SupplementOrderItemEntity item : order.getItems()) {
                SupplementProductEntity product = item.getProduct();
                product.setStockQty(product.getStockQty() + item.getQuantity());
                productRepository.save(product);
            }

            order.setStatus("CANCELLED");
            return mapper.toDto(orderRepository.save(order));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to cancel order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
