package com.bit.backend.controllers;

import com.bit.backend.dtos.BookClassDto;
import com.bit.backend.dtos.MyBookingDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.BookClassServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class BookClassController {

    private final BookClassServiceI bookClassServiceI;

    public BookClassController(BookClassServiceI bookClassServiceI) {
        this.bookClassServiceI = bookClassServiceI;
    }

    @GetMapping("/my-bookings/{userId}")
    public ResponseEntity<List<MyBookingDto>> getMyBookings(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(bookClassServiceI.getMyBookings(userId));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to load your bookings. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/my-bookings/{bookingId}/cancel")
    public ResponseEntity<Void> cancelBooking(
            @PathVariable long bookingId,
            @RequestBody Map<String, Long> body) {
        try {
            Long userId = body.get("userId");
            if (userId == null) {
                throw new AppException("Unable to identify your account. Please log in again.", HttpStatus.BAD_REQUEST);
            }
            bookClassServiceI.cancelBooking(bookingId, userId);
            return ResponseEntity.ok().build();
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to cancel the booking. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/booking-class/confirm/{classId}")
    public ResponseEntity<BookClassDto> confirmBooking(
            @PathVariable long classId,
            @RequestBody Map<String, Long> body) {
        try {
            Long userId = body.get("userId");
            if (userId == null) {
                throw new AppException("Unable to identify your account. Please log in again.", HttpStatus.BAD_REQUEST);
            }
            BookClassDto booking = bookClassServiceI.confirmBooking(classId, userId);
            return ResponseEntity.ok(booking);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Booking failed. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
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
            BookClassDto saved = bookClassServiceI.addBookClassEntity(bookClassDto);
            return ResponseEntity.created(URI.create("/booking-class/" + saved.getId())).body(saved);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Booking failed. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
