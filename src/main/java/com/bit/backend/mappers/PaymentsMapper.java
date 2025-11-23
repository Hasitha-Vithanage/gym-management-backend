package com.bit.backend.mappers;

import com.bit.backend.dtos.PaymentsDto;
import com.bit.backend.entities.PaymentsEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PaymentsMapper {
    PaymentsDto toPaymentsDto(PaymentsEntity paymentsEntity);
    PaymentsEntity toPaymentsEntity(PaymentsDto paymentsDto);
    List<PaymentsDto> toPaymentsDto(List<PaymentsEntity> paymentsEntities);
}
