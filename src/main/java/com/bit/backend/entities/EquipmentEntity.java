package com.bit.backend.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "equipment")
public class EquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String category;

    @Column
    private Long supplier;

    @Column
    private String machineName;

    @ElementCollection
    @CollectionTable(name = "equipment_muscle_groups", joinColumns = @JoinColumn(name = "equipment_id"))
    @Column(name = "muscle_group")
    private List<String> muscleGroups;

    @Column
    private String brandName;

    @Column
    private String model;

    @Column
    private String type;

    @Column
    private String sizeStandard;

    @Column
    private String barLength;

    @Column
    private String weight;

    @Column
    private String equipmentName;

    @Column
    private Integer quantity;

    @Column(name = "`condition`")
    private String condition;

    @Column
    private Date purchaseDate;

    @Column
    private String addedBy;

    @Column
    private String remarks;

    public EquipmentEntity() {
    }

    public EquipmentEntity(long id, String category, Long supplier, String machineName, List<String> muscleGroups, String brandName, String model, String type, String sizeStandard, String barLength, String weight, String equipmentName, Integer quantity, String condition, Date purchaseDate, String addedBy, String remarks) {
        this.id = id;
        this.category = category;
        this.supplier = supplier;
        this.machineName = machineName;
        this.muscleGroups = muscleGroups;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<String> getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(List<String> muscleGroups) {
        this.muscleGroups = muscleGroups;
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
}
