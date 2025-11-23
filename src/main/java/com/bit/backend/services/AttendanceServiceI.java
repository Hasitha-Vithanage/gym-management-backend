package com.bit.backend.services;

import com.bit.backend.dtos.AttendanceDto;

public interface AttendanceServiceI {

    AttendanceDto addAttendanceEntity(AttendanceDto attendanceDto);
}
