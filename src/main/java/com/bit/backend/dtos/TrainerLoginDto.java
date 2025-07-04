package com.bit.backend.dtos;

public class TrainerLoginDto {

    private long id;
    private long trainerId;
    private String code;

    public TrainerLoginDto() {
    }

    public TrainerLoginDto(long id, long trainerId, String code) {
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
