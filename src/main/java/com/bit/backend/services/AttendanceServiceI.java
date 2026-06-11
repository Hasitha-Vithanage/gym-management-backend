package com.bit.backend.services;

import com.bit.backend.dtos.AttendanceDto;

import java.util.List;

public interface AttendanceServiceI {

    AttendanceDto addAttendanceEntity(AttendanceDto attendanceDto);

    List<AttendanceDto> getTodayMemberCheckIns();
}
