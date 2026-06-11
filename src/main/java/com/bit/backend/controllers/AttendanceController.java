package com.bit.backend.controllers;

import com.bit.backend.dtos.AttendanceDto;
import com.bit.backend.services.AttendanceServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttendanceController {

    private final AttendanceServiceI attendanceServiceI;

    public AttendanceController(AttendanceServiceI attendanceServiceI) {
        this.attendanceServiceI = attendanceServiceI;
    }

    @PostMapping("/memberService/mark-attendance/present/{memberNo}")
    public ResponseEntity<AttendanceDto> markMemberAttendancePresent(@PathVariable String memberNo) {
        AttendanceDto dto = new AttendanceDto();
        dto.setAttendanceType("member");
        dto.setMember(memberNo);
        return ResponseEntity.ok(attendanceServiceI.addAttendanceEntity(dto));
    }

    @GetMapping("/memberService/attendance/today")
    public ResponseEntity<List<AttendanceDto>> getTodayCheckIns() {
        return ResponseEntity.ok(attendanceServiceI.getTodayMemberCheckIns());
    }

    @PostMapping("/employeeService/mark-attendance/present/{employeeId}")
    public ResponseEntity<AttendanceDto> markEmployeeAttendancePresentQR(
            @PathVariable String employeeId,
            @RequestBody AttendanceDto dto) {

        dto.setAttendanceType("employee");
        dto.setEmployee(employeeId);
        dto.setAttendanceStatus("present");

        return ResponseEntity.ok(attendanceServiceI.addAttendanceEntity(dto));
    }

    @PostMapping("/employeeService/mark-attendance/{employeeId}")
    public ResponseEntity<AttendanceDto> markEmployeeAttendancePresent(
            @PathVariable String employeeId,
            @RequestBody AttendanceDto dto) {

        dto.setAttendanceType("employee");
        dto.setEmployee(employeeId);

        return ResponseEntity.ok(attendanceServiceI.addAttendanceEntity(dto));
    }
}
