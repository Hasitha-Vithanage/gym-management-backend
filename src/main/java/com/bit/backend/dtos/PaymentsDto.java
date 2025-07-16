package com.bit.backend.dtos;

import java.time.LocalDate;

public class PaymentsDto {

    private long id;
    private String member;
    private String membershipCategory;
    private double amount;
    private LocalDate paymentDate;
    private String status;

    public PaymentsDto() {
    }

    public PaymentsDto(long id, String member, String membershipCategory, double amount, LocalDate paymentDate, String status) {
        this.id = id;
        this.member = member;
        this.membershipCategory = membershipCategory;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getMembershipCategory() {
        return membershipCategory;
    }

    public void setMembershipCategory(String membershipCategory) {
        this.membershipCategory = membershipCategory;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
