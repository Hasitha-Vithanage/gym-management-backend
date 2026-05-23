package com.bit.backend.dtos;

import java.time.LocalDate;

public class AssignTrainerDto {

    private long id;
    private String member;
    private Long memberId;
    private String trainer;
    private Long trainerId;
    private LocalDate date;

    public AssignTrainerDto() {
    }

    public AssignTrainerDto(long id, String member, Long memberId, String trainer, Long trainerId, LocalDate date) {
        this.id = id;
        this.member = member;
        this.memberId = memberId;
        this.trainer = trainer;
        this.trainerId = trainerId;
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
