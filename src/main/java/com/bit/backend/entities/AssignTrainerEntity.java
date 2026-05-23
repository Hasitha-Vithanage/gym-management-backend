package com.bit.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "assign_trainer")
public class AssignTrainerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "member")
    private String member;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "trainer")
    private String trainer;

    @Column(name = "trainer_id")
    private Long trainerId;

    @Column(name = "date", updatable = false)
    @CreationTimestamp
    private LocalDate date;

    public AssignTrainerEntity() {
    }

    public AssignTrainerEntity(long id, String member, Long memberId, String trainer, Long trainerId, LocalDate date) {
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
