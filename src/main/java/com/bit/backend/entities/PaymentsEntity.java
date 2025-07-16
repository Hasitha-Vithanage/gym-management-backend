package com.bit.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class PaymentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "member")
    private String member;

    @Column(name = "membershipCategory")
    private String membershipCategory;

    @Column(name = "amount")
    private double amount;

    @Column(name = "paymentDate", updatable = false)
    @CreationTimestamp
    private LocalDate paymentDate;

    @Column(name = "status")
    private String status;

    public PaymentsEntity() {
    }

    public PaymentsEntity(long id, String member, String membershipCategory, double amount, LocalDate paymentDate, String status) {
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
