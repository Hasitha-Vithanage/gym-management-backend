package com.bit.backend.dtos;

import java.time.LocalDate;

public class TrialRequestDto {

    private long id;
    private String name;
    private String email;
    private String company;
    private String message;

    public TrialRequestDto() {
    }

    public TrialRequestDto(long id, String name, String email, String company, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.company = company;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
