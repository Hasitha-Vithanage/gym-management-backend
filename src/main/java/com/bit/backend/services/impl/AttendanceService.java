package com.bit.backend.services.impl;

import com.bit.backend.dtos.AttendanceDto;
import com.bit.backend.entities.AttendanceEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AttendanceMapper;
import com.bit.backend.repositories.AttendanceRepository;
import com.bit.backend.services.AttendanceServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService implements AttendanceServiceI {

    private static final String MEMBER_TYPE = "member";

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceService(AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
    }

    public AttendanceDto addAttendanceEntity(AttendanceDto attendanceDto) {
        if (MEMBER_TYPE.equals(attendanceDto.getAttendanceType())) {
            boolean alreadyCheckedIn = attendanceRepository.existsByMemberAndAttendanceDateAndAttendanceType(
                    attendanceDto.getMember(), LocalDate.now(), MEMBER_TYPE);
            if (alreadyCheckedIn) {
                throw new AppException("Member already checked in today", HttpStatus.CONFLICT);
            }
        }

        attendanceDto.setAttendanceDate(LocalDate.now());
        attendanceDto.setCheckInTime(LocalDateTime.now());
        attendanceDto.setAttendanceStatus("present");

        try {
            AttendanceEntity entity = attendanceMapper.toAttendanceEntity(attendanceDto);
            AttendanceEntity saved = attendanceRepository.save(entity);
            return attendanceMapper.toAttendanceDto(saved);
        } catch (Exception e) {
            throw new AppException("Failed to save attendance: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<AttendanceDto> getTodayMemberCheckIns() {
        List<AttendanceEntity> entities = attendanceRepository
                .findByAttendanceDateAndAttendanceTypeOrderByCheckInTimeDesc(LocalDate.now(), MEMBER_TYPE);
        return attendanceMapper.toAttendanceDto(entities);
    }
}
