package com.bit.backend.controllers;

import com.bit.backend.dtos.CommonDataDto;
import com.bit.backend.dtos.CommonDataListDto;
import com.bit.backend.entities.CommonDataEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.CommonDataServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common-data-service")
public class CommonServiceController {

    private final CommonDataServiceI commonDataServiceI;

    public CommonServiceController(CommonDataServiceI commonDataServiceI) {
        this.commonDataServiceI = commonDataServiceI;
    }

    @GetMapping("/available-privileges/{id}")
    public List<CommonDataDto> getAvailablePrivilegesByGroupID(@PathVariable int id) {
        List<CommonDataDto> availablePrivileges = commonDataServiceI.getAvailablePrivilegesByGroupID(id);
        return availablePrivileges;
    }

    @GetMapping("/assigned-privileges/{id}")
    public List<CommonDataDto> getAssignedPrivilegesByGroupID(@PathVariable int id) {
        List<CommonDataDto> availablePrivileges = commonDataServiceI.getAssignedPrivilegesByGroupID(id);
        return availablePrivileges;
    }

    @PostMapping("/group-privileges/{id}")
    public CommonDataListDto saveData(@RequestBody CommonDataListDto commonDataListDto, @PathVariable int id) {
        CommonDataListDto savedData = commonDataServiceI.saveData(id, commonDataListDto);
        return savedData;
    }

    @GetMapping("/group-available-users/{id}")
    public List<CommonDataDto> getAvailableUsersByGroupID(@PathVariable int id) {
        List<CommonDataDto> availablePrivileges = commonDataServiceI.getAvailableUsersByGroupID(id);
        return availablePrivileges;
    }

    @GetMapping("/group-assigned-users/{id}")
    public List<CommonDataDto> getAssignedUsersByGroupID(@PathVariable int id) {
        List<CommonDataDto> availablePrivileges = commonDataServiceI.getAssignedUsersByGroupID(id);
        return availablePrivileges;
    }

    @PostMapping("/privilege-group-users/{id}")
    public CommonDataListDto saveGroupUserData(@RequestBody CommonDataListDto commonDataListDto, @PathVariable int id) {
        CommonDataListDto savedData = commonDataServiceI.saveGroupUserData(id, commonDataListDto);
        return savedData;
    }

    @GetMapping("/employee-monthly-attendance")
    public ResponseEntity<List<Map<String, Object>>> getMonthlyEmployeeAttendance() {
        try {
            List<Map<String, Object>> commonTaskStats = commonDataServiceI.getMonthlyEmployeeAttendance();
            return ResponseEntity.ok(commonTaskStats);
        } catch (Exception e) {
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/member-monthly-attendance")
    public ResponseEntity<List<Map<String, Object>>> getMonthlyMemberAttendance() {
        try {
            List<Map<String, Object>> commonTaskStats = commonDataServiceI.getMonthlyMemberAttendance();
            return ResponseEntity.ok(commonTaskStats);
        } catch (Exception e) {
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/suppliment-orders-count-per-month")
    public ResponseEntity<List<Map<String, Object>>> getMonthlySupplimentSalesCount() {
        try {
            List<Map<String, Object>> commonTaskStats = commonDataServiceI.getMonthlySupplimentSalesCount();
            return ResponseEntity.ok(commonTaskStats);
        } catch (Exception e) {
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/suppliment-orders-income-per-month")
    public ResponseEntity<List<Map<String, Object>>> getMonthlySupplimentSalesIncome() {
        try {
            List<Map<String, Object>> commonTaskStats = commonDataServiceI.getMonthlySupplimentSalesIncome();
            return ResponseEntity.ok(commonTaskStats);
        } catch (Exception e) {
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
