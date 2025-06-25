package com.bit.backend.mappers;

import com.bit.backend.dtos.OrderDto;
import com.bit.backend.entities.BillingDetailsEntity;
import com.bit.backend.entities.OrderEntity;
import com.bit.backend.entities.OrderItemEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface OrderMapper {

    // Main Order mappings
    OrderDto toOrderDto(OrderEntity orderEntity);

    OrderEntity toOrderEntity(OrderDto orderDto);

    List<OrderDto> toOrderDtoList(List<OrderEntity> orderEntityList);

    List<OrderEntity> toOrderEntityList(List<OrderDto> orderDtoList);

    // OrderItem mappings
    OrderDto.OrderItemDto toOrderItemDto(OrderItemEntity orderItemEntity);

    OrderItemEntity toOrderItemEntity(OrderDto.OrderItemDto orderItemDto);

    List<OrderDto.OrderItemDto> toOrderItemDtoList(List<OrderItemEntity> orderItemEntityList);

    List<OrderItemEntity> toOrderItemEntityList(List<OrderDto.OrderItemDto> orderItemDtoList);

    // BillingDetails mappings
    OrderDto.BillingDetailsDto toBillingDetailsDto(BillingDetailsEntity billingDetailsEntity);

    BillingDetailsEntity toBillingDetailsEntity(OrderDto.BillingDetailsDto billingDetailsDto);

    List<OrderDto.BillingDetailsDto> toBillingDetailsDtoList(List<BillingDetailsEntity> billingDetailsEntityList);

    List<BillingDetailsEntity> toBillingDetailsEntityList(List<OrderDto.BillingDetailsDto> billingDetailsDtoList);

}
