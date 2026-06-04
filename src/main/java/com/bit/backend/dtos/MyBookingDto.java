package com.bit.backend.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class MyBookingDto {

    // Booking fields
    private Long   bookingId;
    private Long   userId;
    private String bookingStatus;
    private LocalDate bookedDate;

    // Class fields
    private Long      classId;
    private String    classTitle;
    private String    classType;
    private String    description;
    private LocalDate classDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String    conductorName;
    private Long      trainerEmployeeId;
    private String    classStatus;
    private Integer   totalSlots;
    private Integer   remainingSlots;

    public MyBookingDto() {
        // Required for serialization
    }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }

    public LocalDate getBookedDate() { return bookedDate; }
    public void setBookedDate(LocalDate bookedDate) { this.bookedDate = bookedDate; }

    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }

    public String getClassTitle() { return classTitle; }
    public void setClassTitle(String classTitle) { this.classTitle = classTitle; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getClassDate() { return classDate; }
    public void setClassDate(LocalDate classDate) { this.classDate = classDate; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getConductorName() { return conductorName; }
    public void setConductorName(String conductorName) { this.conductorName = conductorName; }

    public Long getTrainerEmployeeId() { return trainerEmployeeId; }
    public void setTrainerEmployeeId(Long trainerEmployeeId) { this.trainerEmployeeId = trainerEmployeeId; }

    public String getClassStatus() { return classStatus; }
    public void setClassStatus(String classStatus) { this.classStatus = classStatus; }

    public Integer getTotalSlots() { return totalSlots; }
    public void setTotalSlots(Integer totalSlots) { this.totalSlots = totalSlots; }

    public Integer getRemainingSlots() { return remainingSlots; }
    public void setRemainingSlots(Integer remainingSlots) { this.remainingSlots = remainingSlots; }
}
