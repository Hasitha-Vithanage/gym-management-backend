package com.bit.backend.dtos;

import java.util.List;

public class SupplementProductDto {

    private Long id;
    private String productName;
    private String brand;
    private String category;
    private String description;
    private Double price;
    private Integer stockQty;
    private List<String> tags;
    private Boolean isActive;
    private Boolean isDeleted;

    public SupplementProductDto() {}

    public SupplementProductDto(Long id, String productName, String brand, String category,
                                String description, Double price, Integer stockQty,
                                List<String> tags, Boolean isActive, Boolean isDeleted) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.category = category;
        this.description = description;
        this.price = price;
        this.stockQty = stockQty;
        this.tags = tags;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStockQty() { return stockQty; }
    public void setStockQty(Integer stockQty) { this.stockQty = stockQty; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
}
