package com.bit.backend.controllers;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.dtos.PaymentsDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.PaymentsServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class PaymentsController {

    private final PaymentsServiceI paymentsServiceI;

    public PaymentsController(PaymentsServiceI paymentsServiceI) {
        this.paymentsServiceI = paymentsServiceI;
    }

    @PostMapping("/add-payment")
    public ResponseEntity<PaymentsDto> addPayments(@RequestBody PaymentsDto paymentsDto) {
        try {
            PaymentsDto paymentsDtoResponse = paymentsServiceI.addPaymentsEntity(paymentsDto);
            return ResponseEntity.created(URI.create("/add-payment" + paymentsDtoResponse.getMember())).body(paymentsDtoResponse);
        } catch (Exception e) {
            throw new AppException("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentsDto>> getPayments() {
        try {
            List<PaymentsDto> paymentsDtoList = paymentsServiceI.getPayments();
            return ResponseEntity.ok(paymentsDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to load Payments records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("add-payment/{id}")
    public ResponseEntity<PaymentsDto> updatePayments(@PathVariable long id, @RequestBody PaymentsDto paymentsDto) {
        try {
            PaymentsDto paymentsDtoResponse = paymentsServiceI.updatePayments(id, paymentsDto);
            return ResponseEntity.ok(paymentsDtoResponse);
        } catch (Exception e) {
            throw new AppException("Failed to update the Payments information. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<PaymentsDto> deletePayments(@PathVariable long id) {
        PaymentsDto paymentsDto = paymentsServiceI.deletePayments(id);
        return ResponseEntity.ok().body(paymentsDto);
    }
}
