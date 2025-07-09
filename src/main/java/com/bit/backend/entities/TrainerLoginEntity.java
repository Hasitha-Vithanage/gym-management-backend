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

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "userName")
    private String userName;

    public TrainerLoginEntity() {
    }

    public TrainerLoginEntity(long id, long trainerId, String firstName, String lastName, String userName) {
        this.id = id;
        this.trainerId = trainerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
