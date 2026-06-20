package com.bit.backend.controllers;

import com.bit.backend.dtos.FeedbackDto;
import com.bit.backend.dtos.FeedbackStatusUpdateDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.FeedbackServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackServiceI feedbackService;

    public FeedbackController(FeedbackServiceI feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackDto> addFeedback(@RequestBody FeedbackDto feedbackDto) {
        FeedbackDto saved = feedbackService.addFeedbackEntry(feedbackDto);
        return ResponseEntity.created(URI.create("/feedback/" + saved.getId())).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbackEntries());
    }

    @GetMapping("/my/{submittedBy}")
    public ResponseEntity<List<FeedbackDto>> getMyFeedbacks(@PathVariable String submittedBy) {
        return ResponseEntity.ok(feedbackService.getFeedbacksBySubmittedBy(submittedBy));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable long id, @RequestBody FeedbackDto feedbackDto) {
        try {
            return ResponseEntity.ok(feedbackService.updateFeedback(id, feedbackDto));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to update feedback: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<FeedbackDto> updateStatus(@PathVariable long id, @RequestBody FeedbackStatusUpdateDto statusUpdateDto) {
        try {
            return ResponseEntity.ok(feedbackService.updateStatus(id, statusUpdateDto));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to update status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FeedbackDto> deleteFeedback(@PathVariable long id) {
        try {
            return ResponseEntity.ok(feedbackService.deleteFeedback(id));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to delete feedback: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getAnalytics() {
        return ResponseEntity.ok(feedbackService.getAnalytics());
    }
}
