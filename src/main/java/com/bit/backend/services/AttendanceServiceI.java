package com.bit.backend.services;

import com.bit.backend.dtos.AttendanceDto;

import java.util.List;
import java.util.Map;

public interface AttendanceServiceI {

    AttendanceDto addAttendanceEntity(AttendanceDto attendanceDto);

    List<AttendanceDto> getTodayMemberCheckIns();

    List<AttendanceDto> getMemberAttendanceHistory(String memberNo, int year, int month);

    List<Map<String, Object>> getDailyMemberCheckIns(int year, int month);

    List<Map<String, Object>> getMemberPeakHours();

    List<Map<String, Object>> getAtRiskMembers(int days);
}
