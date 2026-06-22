package com.bit.backend.dtos;

import java.time.LocalDate;

public class MaintenanceLogDto {

    private Long id;
    private Long equipmentId;
    private String type;
    private String description;
    private String performedBy;
    private LocalDate performedDate;
    private Double cost;

    public MaintenanceLogDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEquipmentId() { return equipmentId; }
    public void setEquipmentId(Long equipmentId) { this.equipmentId = equipmentId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }

    public LocalDate getPerformedDate() { return performedDate; }
    public void setPerformedDate(LocalDate performedDate) { this.performedDate = performedDate; }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
}
