package com.bit.backend.dtos;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {
    // Order Data
    private long orderId;
    private LocalDate date;
    private double totalCost;
    private String orderedBy;

    // List of Order Items
    private List<OrderItemDto> orderItems;

    // Billing Details
    private BillingDetailsDto billingDetails;

    // Getters and Setters

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public BillingDetailsDto getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetailsDto billingDetails) {
        this.billingDetails = billingDetails;
    }

    // Inner DTO classes for OrderItem and BillingDetails

    public static class OrderItemDto {
        private long orderItemId;
        private long orderId;         // This can be omitted on the frontend, backend sets it
        private long productId;
        private int quantity;
        private String productName;
        private double unitPrice;
        private double totalPrice;

        // Getters and Setters
        public long getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(long orderItemId) {
            this.orderItemId = orderItemId;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public long getProductId() {
            return productId;
        }

        public void setProductId(long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    public static class BillingDetailsDto {
        private long billingId;
        private long orderId;       // This also can be omitted on the frontend, backend sets it
        private String firstName;
        private String lastName;
        private String address;
        private String phone;
        private String email;
        private String note;

        // Getters and Setters
        public long getBillingId() {
            return billingId;
        }

        public void setBillingId(long billingId) {
            this.billingId = billingId;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}


