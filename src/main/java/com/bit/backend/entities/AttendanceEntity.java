package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attendanceDate")
    private LocalDate attendanceDate;

    @Column(name = "attendanceType") // "employee" or "member"
    private String attendanceType;

    @Column(name = "employee") // employeeId or memberNo
    private String employee;

    @Column(name = "member") // employeeId or memberNo
    private String member;

    @Column(name = "attendanceStatus") // "present" or "absent"
    private String attendanceStatus;

    public AttendanceEntity() {
    }

    public AttendanceEntity(Long id, LocalDate attendanceDate, String attendanceType, String employee, String member, String attendanceStatus) {
        this.id = id;
        this.attendanceDate = attendanceDate;
        this.attendanceType = attendanceType;
        this.employee = employee;
        this.member = member;
        this.attendanceStatus = attendanceStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
