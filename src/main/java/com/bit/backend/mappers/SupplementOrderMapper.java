package com.bit.backend.mappers;

import com.bit.backend.dtos.SupplementOrderDto;
import com.bit.backend.dtos.SupplementOrderItemDto;
import com.bit.backend.entities.SupplementOrderEntity;
import com.bit.backend.entities.SupplementOrderItemEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SupplementOrderMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.productName", target = "productName")
    SupplementOrderItemDto toItemDto(SupplementOrderItemEntity entity);

    SupplementOrderDto toDto(SupplementOrderEntity entity);
    List<SupplementOrderDto> toDtoList(List<SupplementOrderEntity> entities);
}
