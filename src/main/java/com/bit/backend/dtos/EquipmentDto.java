package com.bit.backend.dtos;

import java.time.LocalDate;
import java.util.Date;

public class EquipmentDto {

    private Long id;
    private String category;
    private Long supplier;
    private String machineName;
    private String brandName;
    private String model;
    private String type;
    private String sizeStandard;
    private String barLength;
    private String weight;
    private String equipmentName;
    private Integer quantity;
    private String condition;
    private Date purchaseDate;
    private String addedBy;
    private String remarks;
    private String status;
    private LocalDate lastMaintenance;
    private LocalDate nextMaintenance;

    public EquipmentDto() {
    }

    public EquipmentDto(Long id, String category, Long supplier, String machineName, String brandName, String model, String type, String sizeStandard, String barLength, String weight, String equipmentName, Integer quantity, String condition, Date purchaseDate, String addedBy, String remarks) {
        this.id = id;
        this.category = category;
        this.supplier = supplier;
        this.machineName = machineName;
        this.brandName = brandName;
        this.model = model;
        this.type = type;
        this.sizeStandard = sizeStandard;
        this.barLength = barLength;
        this.weight = weight;
        this.equipmentName = equipmentName;
        this.quantity = quantity;
        this.condition = condition;
        this.purchaseDate = purchaseDate;
        this.addedBy = addedBy;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getSupplier() {
        return supplier;
    }

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSizeStandard() {
        return sizeStandard;
    }

    public void setSizeStandard(String sizeStandard) {
        this.sizeStandard = sizeStandard;
    }

    public String getBarLength() {
        return barLength;
    }

    public void setBarLength(String barLength) {
        this.barLength = barLength;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(LocalDate lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public LocalDate getNextMaintenance() {
        return nextMaintenance;
    }

    public void setNextMaintenance(LocalDate nextMaintenance) {
        this.nextMaintenance = nextMaintenance;
    }
}
