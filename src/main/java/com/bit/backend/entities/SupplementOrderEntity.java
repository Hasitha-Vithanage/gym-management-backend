package com.bit.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supplement_order")
public class SupplementOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_username", nullable = false)
    private String memberUsername;

    @Column(name = "status", nullable = false)
    private String status = "PENDING";

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "notes", length = 500)
    private String notes;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplementOrderItemEntity> items = new ArrayList<>();

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    public SupplementOrderEntity() {}

    public SupplementOrderEntity(Long id, String memberUsername, String status,
                                 LocalDateTime orderDate, Double totalAmount,
                                 String notes, List<SupplementOrderItemEntity> items,
                                 Boolean isDeleted) {
        this.id = id;
        this.memberUsername = memberUsername;
        this.status = status;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.notes = notes;
        this.items = items;
        this.isDeleted = isDeleted;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMemberUsername() { return memberUsername; }
    public void setMemberUsername(String memberUsername) { this.memberUsername = memberUsername; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<SupplementOrderItemEntity> getItems() { return items; }
    public void setItems(List<SupplementOrderItemEntity> items) { this.items = items; }

    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
}
