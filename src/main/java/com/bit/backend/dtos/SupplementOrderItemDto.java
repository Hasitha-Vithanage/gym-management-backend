package com.bit.backend.dtos;

public class SupplementOrderItemDto {

    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double priceAtOrder;

    public SupplementOrderItemDto() {}

    public SupplementOrderItemDto(Long id, Long productId, String productName,
                                  Integer quantity, Double priceAtOrder) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPriceAtOrder() { return priceAtOrder; }
    public void setPriceAtOrder(Double priceAtOrder) { this.priceAtOrder = priceAtOrder; }
}
