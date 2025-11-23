package com.bit.backend.dtos;

import java.time.LocalDate;

public class AttendanceDto {

    private Long id; // Optional if updating
    private LocalDate attendanceDate;
    private String attendanceType;
    private String employee;
    private String member;
    private String attendanceStatus;

    public AttendanceDto() {
    }

    public AttendanceDto(Long id, LocalDate attendanceDate, String attendanceType, String employee, String member, String attendanceStatus) {
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
