package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance_log")
public class MaintenanceLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equipment_id")
    private Long equipmentId;

    @Column(name = "type")
    private String type;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "performed_by")
    private String performedBy;

    @Column(name = "performed_date")
    private LocalDate performedDate;

    @Column(name = "cost")
    private Double cost;

    public MaintenanceLogEntity() {}

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
