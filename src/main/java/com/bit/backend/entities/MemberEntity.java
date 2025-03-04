package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MemberNo")
    private String memberNo;

    @Column(name = "fistName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "nic")
    private String nic;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "emergencyContactNumber")
    private String emergencyContactNumber;

    @Column(name = "bloodType")
    private String bloodType;

    @Column(name = "joinedDate")
    private LocalDate joinedDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "injuries")
    private String injuries;

    @Column(name = "membershipCategory")
    private String membershipCategory;

    public MemberEntity() {
    }

    public MemberEntity(Long id, String memberNo, String firstName, String lastName, String nic, LocalDate dateOfBirth, String address, String phoneNumber, String email, String emergencyContactNumber, String bloodType, LocalDate joinedDate, String gender, String injuries, String membershipCategory) {
        this.id = id;
        this.memberNo = memberNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.emergencyContactNumber = emergencyContactNumber;
        this.bloodType = bloodType;
        this.joinedDate = joinedDate;
        this.gender = gender;
        this.injuries = injuries;
        this.membershipCategory = membershipCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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
