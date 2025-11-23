package com.bit.backend.dtos;

import java.time.LocalDate;

public class AssignTrainerDto {

    private long id;
    private String member;
    private String trainer;
    private LocalDate date;

    public AssignTrainerDto() {
    }

    public AssignTrainerDto(long id, String member, String trainer, LocalDate date) {
        this.id = id;
        this.member = member;
        this.trainer = trainer;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
