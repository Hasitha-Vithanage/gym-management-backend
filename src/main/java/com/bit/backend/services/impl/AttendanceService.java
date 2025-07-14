package com.bit.backend.services.impl;

import com.bit.backend.dtos.AttendanceDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.entities.AttendanceEntity;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AttendanceMapper;
import com.bit.backend.repositories.AttendanceRepository;
import com.bit.backend.services.AttendanceServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService implements AttendanceServiceI {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceService(AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
    }

    public AttendanceDto addAttendanceEntity(AttendanceDto attendanceDto) {
        try {
            System.out.println("************ In Service *************");

            AttendanceEntity attendanceEntity = attendanceMapper.toAttendanceEntity(attendanceDto);
            AttendanceEntity savedItem = attendanceRepository.save(attendanceEntity);
            AttendanceDto savedAttendanceDto = attendanceMapper.toAttendanceDto(savedItem);
            return savedAttendanceDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
