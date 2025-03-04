package com.bit.backend.dtos;

import java.time.LocalDate;

public class MemberDto {

    private long id;
    private String memberNo;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private String nic;
    private String emergencyContactNumber;
    private String bloodType;
    private LocalDate joinedDate;
    private String gender;
    private String injuries;
    private String membershipCategory;

    public MemberDto() {
    }

    public MemberDto(long id, String memberNo, String firstName, String lastName, LocalDate dateOfBirth, String address, String phoneNumber, String email, String nic, String emergencyContactNumber, String bloodType, LocalDate joinedDate, String gender, String injuries, String membershipCategory) {
        this.id = id;
        this.memberNo = memberNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nic = nic;
        this.emergencyContactNumber = emergencyContactNumber;
        this.bloodType = bloodType;
        this.joinedDate = joinedDate;
        this.gender = gender;
        this.injuries = injuries;
        this.membershipCategory = membershipCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public String getMembershipCategory() {
        return membershipCategory;
    }

    public void setMembershipCategory(String membershipCategory) {
        this.membershipCategory = membershipCategory;
    }
}
