package com.bit.backend.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddClassDto {

    private long id;
    private String classTitle;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String conductorName;
    private String profession;
    private int totalSlots;
    private int remainingSlots;
    private BigDecimal fee;
    private String status;

    public AddClassDto() {
    }

    public AddClassDto(long id, String classTitle, String description, LocalDate date, LocalTime startTime, LocalTime endTime, String conductorName, String profession, int totalSlots, int remainingSlots, BigDecimal fee, String status) {
        this.id = id;
        this.classTitle = classTitle;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.conductorName = conductorName;
        this.profession = profession;
        this.totalSlots = totalSlots;
        this.remainingSlots = remainingSlots;
        this.fee = fee;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getConductorName() {
        return conductorName;
    }

    public void setConductorName(String conductorName) {
        this.conductorName = conductorName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getRemainingSlots() {
        return remainingSlots;
    }

    public void setRemainingSlots(int remainingSlots) {
        this.remainingSlots = remainingSlots;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
