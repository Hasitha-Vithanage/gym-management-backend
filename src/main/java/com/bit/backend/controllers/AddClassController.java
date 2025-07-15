package com.bit.backend.controllers;

import com.bit.backend.dtos.AddClassDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.SupplementInventoryDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.AddClassServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class AddClassController {

    private final AddClassServiceI addClassServiceI;

    public AddClassController(AddClassServiceI addClassServiceI) {
        this.addClassServiceI = addClassServiceI;
    }

    @PostMapping("/add-class")
    public ResponseEntity<AddClassDto> addClass(@RequestBody AddClassDto addClassDto) {
        try {
            AddClassDto addClassDtoResponse = addClassServiceI.addAddClassEntity(addClassDto);
            return ResponseEntity.created(URI.create("/add-class" + addClassDtoResponse.getId())).body(addClassDtoResponse);
        } catch (Exception e) {
            throw new AppException("Class Scheduling failed. Please try again later. " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/add-class")
    public ResponseEntity<List<AddClassDto>> getAllClasses() {
try{
    List<AddClassDto> addClassDtoList = addClassServiceI.getAddClass();
    return ResponseEntity.ok(addClassDtoList);
}
catch(Exception e){
    throw new AppException("Failed to load classes.", HttpStatus.INTERNAL_SERVER_ERROR);
}
    }

    @PutMapping("/add-class/{id}")
    public ResponseEntity<AddClassDto> updateAddClass(@PathVariable long id, @RequestBody AddClassDto addClassDto) {
        try {
            AddClassDto addClassDtoResponse = addClassServiceI.updateAddClass(id, addClassDto);
            return ResponseEntity.ok(addClassDtoResponse);
        } catch (Exception e) {
            throw new AppException("Failed to update the class information. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book-class-submit/{id}")
    public ResponseEntity<AddClassDto> getAddClassById(@PathVariable long id) {
        AddClassDto addClassDto = addClassServiceI.getClassById(id);
        return ResponseEntity.ok().body(addClassDto);
    }
}
