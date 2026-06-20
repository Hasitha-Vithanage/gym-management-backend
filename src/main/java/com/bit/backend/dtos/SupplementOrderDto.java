package com.bit.backend.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class SupplementOrderDto {

    private Long id;
    private String memberUsername;
    private String status;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private String notes;
    private List<SupplementOrderItemDto> items;
    private Boolean isDeleted;

    public SupplementOrderDto() {}

    public SupplementOrderDto(Long id, String memberUsername, String status,
                              LocalDateTime orderDate, Double totalAmount,
                              String notes, List<SupplementOrderItemDto> items,
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

    public List<SupplementOrderItemDto> getItems() { return items; }
    public void setItems(List<SupplementOrderItemDto> items) { this.items = items; }

    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
}
