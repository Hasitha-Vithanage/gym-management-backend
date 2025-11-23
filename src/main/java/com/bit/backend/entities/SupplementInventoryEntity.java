package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "supplement_inventory")
public class SupplementInventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "brand")
    private String brand;

    @Column(name = "category")
    private String category;

    @Column(name = "costPrice")
    private int costPrice;

    @Column(name = "retailPrice")
    private int retailPrice;

    @Column(name = "quantityInStock")
    private int quantityInStock;

    @Column(name = "expiryDate")
    private LocalDate expiryDate;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "description")
    private String description;

    @Column(name = "quantityPerUnit")
    private int quantityPerUnit;

    @Column(name = "unit")
    private String unit;

    // Image fields (can be large, use @Lob)
    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;

    public SupplementInventoryEntity() {
    }

    public SupplementInventoryEntity(Long id, String productName, String brand, String category, int costPrice, int retailPrice, int quantityInStock, LocalDate expiryDate, String supplier, String description, int quantityPerUnit, String unit, byte[] image, String imageName, String imageType) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.category = category;
        this.costPrice = costPrice;
        this.retailPrice = retailPrice;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
        this.supplier = supplier;
        this.description = description;
        this.quantityPerUnit = quantityPerUnit;
        this.unit = unit;
        this.image = image;
        this.imageName = imageName;
        this.imageType = imageType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(int quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
