package com.bit.backend.services;

import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.PaymentsDto;

import java.util.List;

public interface PaymentsServiceI {

    PaymentsDto addPaymentsEntity(PaymentsDto paymentsDto);
    PaymentsDto updatePayments(long id, PaymentsDto paymentsDto);
    List<PaymentsDto> getPayments();
    PaymentsDto deletePayments(long id);
}
