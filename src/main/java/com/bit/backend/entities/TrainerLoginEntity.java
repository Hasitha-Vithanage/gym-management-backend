package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "trainer-login")
public class TrainerLoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "trainerId")
    private long trainerId;

    @Column(name = "code")
    private String code;

    public TrainerLoginEntity() {
    }

    public TrainerLoginEntity(long id, long trainerId, String code) {
        this.id = id;
        this.trainerId = trainerId;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
