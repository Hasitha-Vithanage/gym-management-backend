package com.bit.backend.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "supplement_product")
public class SupplementProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 150)
    private String productName;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock_qty", nullable = false)
    private Integer stockQty = 0;

    @ElementCollection
    @CollectionTable(name = "supplement_product_tags", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    private List<String> tags;

    @Column(name = "is_active")
    private Boolean isActive = Boolean.TRUE;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    public SupplementProductEntity() {}

    public SupplementProductEntity(Long id, String productName, String brand, String category,
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
