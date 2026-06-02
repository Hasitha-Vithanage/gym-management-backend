package com.bit.backend.controllers;

import com.bit.backend.dtos.AddClassDto;
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
            AddClassDto response = addClassServiceI.addAddClassEntity(addClassDto);
            return ResponseEntity.created(URI.create("/add-class/" + response.getId())).body(response);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to schedule the class. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/add-class")
    public ResponseEntity<List<AddClassDto>> getAllClasses() {
        try {
            return ResponseEntity.ok(addClassServiceI.getAddClass());
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to load classes. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/add-class/{id}")
    public ResponseEntity<AddClassDto> updateAddClass(@PathVariable long id, @RequestBody AddClassDto addClassDto) {
        try {
            return ResponseEntity.ok(addClassServiceI.updateAddClass(id, addClassDto));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to update the class. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book-class-submit/{id}")
    public ResponseEntity<AddClassDto> getAddClassById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(addClassServiceI.getClassById(id));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to load class details. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/add-class/{id}")
    public ResponseEntity<AddClassDto> deleteClass(@PathVariable long id) {
        try {
            return ResponseEntity.ok(addClassServiceI.deleteAddClass(id));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to delete the class. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
