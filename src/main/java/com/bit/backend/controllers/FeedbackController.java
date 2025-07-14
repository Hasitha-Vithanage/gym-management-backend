package com.bit.backend.controllers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.FeedbackDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.repositories.FeedbackRepository;
import com.bit.backend.services.FeedbackServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class FeedbackController {

    private final FeedbackServiceI feedbackServiceI;
    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackServiceI feedbackServiceI, FeedbackRepository feedbackRepository) {
        this.feedbackServiceI = feedbackServiceI;
        this.feedbackRepository = feedbackRepository;
    }

    @PostMapping("/ratings&feedback")
    public ResponseEntity<FeedbackDto> addForm(@RequestBody FeedbackDto feedbackDto) {
        FeedbackDto feedbackDtoResponse = feedbackServiceI.addFeedbackEntry(feedbackDto);
        return ResponseEntity.created(URI.create("/ratings&feedback"+feedbackDtoResponse.getId())).body(feedbackDtoResponse);
    }


    @GetMapping("/ratings&feedback")
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks() {
        List<FeedbackDto> feedbackDtoList = feedbackServiceI.getAllFeedbackEntries();
        return ResponseEntity.ok(feedbackDtoList);
    }

    @GetMapping("/ratings&feedback/{userName}")
    public ResponseEntity<List<FeedbackDto>> getFeedbacksByUserName(@PathVariable String userName) {
        List<FeedbackDto> feedbackDtoList = feedbackServiceI.getFeedbacksByUserName(userName);
        return ResponseEntity.ok(feedbackDtoList);
    }

    @PutMapping("ratings&feedback/{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable long id, @RequestBody FeedbackDto feedbackDto) {
        try {
            FeedbackDto feedbackDtoResponse = feedbackServiceI.updateFeedback(id, feedbackDto);
            return ResponseEntity.ok(feedbackDtoResponse);
        } catch (Exception e) {
            throw new AppException("Failed to update the feedback information. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("ratings&feedback/{id}")
    public ResponseEntity<FeedbackDto> deleteFeedback(@PathVariable long id) {
        try {
            FeedbackDto feedbackDto = feedbackServiceI.deleteFeedback(id);
            return ResponseEntity.ok(feedbackDto);
        } catch (Exception e) {
            throw new AppException("Failed to delete the feedback record. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
