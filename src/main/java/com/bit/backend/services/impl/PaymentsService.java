package com.bit.backend.services.impl;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.PaymentsDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.PaymentsEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.PaymentsMapper;
import com.bit.backend.repositories.PaymentsRepository;
import com.bit.backend.services.PaymentsServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentsService implements PaymentsServiceI {

    private final PaymentsRepository paymentsRepository;
    private final PaymentsMapper paymentsMapper;

    public PaymentsService(PaymentsRepository paymentsRepository, PaymentsMapper paymentsMapper) {
        this.paymentsRepository = paymentsRepository;
        this.paymentsMapper = paymentsMapper;
    }

    @Override
    public PaymentsDto addPaymentsEntity(PaymentsDto paymentsDto) {
        try {
            System.out.println("************ In Service *************");

            // Check if this member already has a trainer assigned
            boolean alreadyPaid = paymentsRepository.existsByMember(paymentsDto.getMember());
            if (alreadyPaid) {
                throw new AppException("This member is already paid.", HttpStatus.CONFLICT);
            }

            PaymentsEntity paymentsEntity = paymentsMapper.toPaymentsEntity(paymentsDto);
            PaymentsEntity savedItem = paymentsRepository.save(paymentsEntity);
            return paymentsMapper.toPaymentsDto(savedItem);

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<PaymentsDto> getPayments() {
        try {
            // db operations and send data
            List<PaymentsEntity> paymentsEntityList = paymentsRepository.findAll();
            List<PaymentsDto> paymentsDtoList = paymentsMapper.toPaymentsDto(paymentsEntityList);
            return paymentsDtoList;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public PaymentsDto updatePayments(long id, PaymentsDto paymentsDto) {
        try {
            Optional<PaymentsEntity> optionalPaymentsEntity = paymentsRepository.findById(id);

            if (!optionalPaymentsEntity.isPresent()) {
                throw new AppException("Payment Does Not Exist", HttpStatus.BAD_REQUEST);
            }

            PaymentsEntity newPaymentsEntity = paymentsMapper.toPaymentsEntity(paymentsDto);
            newPaymentsEntity.setId(id);
            PaymentsEntity paymentsEntity = paymentsRepository.save(newPaymentsEntity);
            PaymentsDto responsePaymentsDto = paymentsMapper.toPaymentsDto(paymentsEntity);
            return responsePaymentsDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
