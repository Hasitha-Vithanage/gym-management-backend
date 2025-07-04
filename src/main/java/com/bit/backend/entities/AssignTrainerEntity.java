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

    @Column(name = "trainer")
    private String trainer;

    @Column(name = "date", updatable = false)
    @CreationTimestamp
    private LocalDate date;

    public AssignTrainerEntity() {
    }

    public AssignTrainerEntity(long id, String member, String trainer, LocalDate date) {
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
