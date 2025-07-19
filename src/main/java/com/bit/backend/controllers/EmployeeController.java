package com.bit.backend.controllers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.EmployeeServiceI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeServiceI employeeServiceI;

    public EmployeeController(EmployeeServiceI employeeServiceI) {
        this.employeeServiceI = employeeServiceI;
    }

    @PostMapping(value = {"/employee"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EmployeeDto> addEmployee(@RequestPart("employeeForm") EmployeeDto employeeDto, @RequestPart("image") MultipartFile file) {
        try {
            employeeDto.setImage(file.getBytes());
            employeeDto.setImageName(file.getOriginalFilename());
            employeeDto.setImageType(file.getContentType());
            EmployeeDto employeeDtoResponse = employeeServiceI.addEmployeeEntity(employeeDto);
            return ResponseEntity.created(URI.create("/employee"+employeeDtoResponse.getFirstName())).body(employeeDtoResponse);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        try {
            List<EmployeeDto> employeeDtoList = employeeServiceI.getEmployee();
            return ResponseEntity.ok(employeeDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to load employee records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/trainer/{id}")
    public ResponseEntity<EmployeeDto> getTrainerById(@PathVariable long id) {
        EmployeeDto trainer = employeeServiceI.getTrainerById(id);
        return ResponseEntity.ok().body(trainer);
    }

    @GetMapping("/getTrainers")
    public ResponseEntity<List<EmployeeDto>> getTrainers() {
        // calling service through interface

        List<EmployeeDto>  employeeDtoList = employeeServiceI.getTrainers();
        return ResponseEntity.ok(employeeDtoList);
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable long id, @RequestPart("employeeForm") EmployeeDto employeeDto, @RequestPart("image") MultipartFile file) {
        try {
            employeeDto.setImage(file.getBytes());
            employeeDto.setImageName(file.getOriginalFilename());
            employeeDto.setImageType(file.getContentType());
            EmployeeDto employeeDtoResponse = employeeServiceI.updateEmployee(id, employeeDto);
            return ResponseEntity.ok(employeeDtoResponse);
        } catch (Exception e) {
            throw new AppException("Failed to update the employee information. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable long id) {
        try {
            EmployeeDto employeeDto = employeeServiceI.deleteEmployee(id);
            return ResponseEntity.ok(employeeDto);
        } catch (Exception e) {
            throw new AppException("Failed to delete the employee record. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee-count")
    public ResponseEntity<Long> getEmployeeCount() {
        try {
            long employeeCount = employeeServiceI.getEmployeeCount();
            return ResponseEntity.ok(employeeCount);
        } catch (Exception e) {
            throw new AppException("Failed to get Employee Count", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
