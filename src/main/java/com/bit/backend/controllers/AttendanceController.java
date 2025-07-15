package com.bit.backend.controllers;

import com.bit.backend.dtos.AttendanceDto;
import com.bit.backend.services.AssignTrainerServiceI;
import com.bit.backend.services.AttendanceServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {

    private final AttendanceServiceI attendanceServiceI;

    public AttendanceController(AttendanceServiceI attendanceServiceI) {
        this.attendanceServiceI = attendanceServiceI;
    }

    @PostMapping("/memberService/mark-attendance/present/{memberNo}")
    public ResponseEntity<AttendanceDto> markMemberAttendancePresent(
            @PathVariable String memberNo,
            @RequestBody AttendanceDto dto) {

        dto.setAttendanceType("member");
        dto.setMember(memberNo);
//        dto.setAttendanceStatus("present");

        AttendanceDto saved = attendanceServiceI.addAttendanceEntity(dto);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/employeeService/mark-attendance/present/{employeeId}")
    public ResponseEntity<AttendanceDto> markEmployeeAttendancePresentQR(
            @PathVariable String employeeId,
            @RequestBody AttendanceDto dto) {

        dto.setAttendanceType("employee");
        dto.setEmployee(employeeId);
        dto.setAttendanceStatus("present");

        AttendanceDto saved = attendanceServiceI.addAttendanceEntity(dto);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/employeeService/mark-attendance/{employeeId}")
    public ResponseEntity<AttendanceDto> markEmployeeAttendancePresent(
            @PathVariable String employeeId,
            @RequestBody AttendanceDto dto) {

        dto.setAttendanceType("employee");
        dto.setEmployee(employeeId);
        dto.setAttendanceStatus(dto.getAttendanceStatus());

        AttendanceDto saved = attendanceServiceI.addAttendanceEntity(dto);
        return ResponseEntity.ok(saved);
    }
}
