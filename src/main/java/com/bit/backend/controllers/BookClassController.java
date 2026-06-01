package com.bit.backend.controllers;

import com.bit.backend.dtos.BookClassDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.BookClassServiceI;
import com.bit.backend.services.impl.BookClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import java.net.URI;

@RestController
public class BookClassController {

    private final BookClassServiceI bookClassServiceI;

    public BookClassController(BookClassServiceI bookClassServiceI) {
        this.bookClassServiceI = bookClassServiceI;
    }

    @PostMapping("/booking-class/confirm/{classId}")
    public ResponseEntity<BookClassDto> confirmBooking(
            @PathVariable long classId,
            @RequestBody Map<String, Long> body) {
        try {
            Long userId = body.get("userId");
            if (userId == null) {
                throw new AppException("userId is required", HttpStatus.BAD_REQUEST);
            }
            BookClassDto booking = bookClassServiceI.confirmBooking(classId, userId);
            return ResponseEntity.ok(booking);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Booking failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/booking-class", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BookClassDto> addBookClass(
            @RequestPart("bookingForm") BookClassDto bookClassDto,
            @RequestPart("payslip") MultipartFile file) {
        try {
            bookClassDto.setImage(file.getBytes());
            bookClassDto.setImageName(file.getOriginalFilename());
            bookClassDto.setImageType(file.getContentType());

            BookClassDto savedBooking = bookClassServiceI.addBookClassEntity(bookClassDto);

            return ResponseEntity.created(URI.create("/booking-class/" + savedBooking.getId()))
                    .body(savedBooking);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
