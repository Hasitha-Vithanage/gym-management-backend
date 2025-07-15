package com.bit.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class BookClassDto {

    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phoneNumber;
    private String nic;
    private String emergencyContactNumber;
    private String bloodType;
    private LocalDate joinedDate;
    private String gender;
    private String injuries;
    private String membershipCategory;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private byte[] image;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imageName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imageType;
}
