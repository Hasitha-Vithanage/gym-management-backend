package com.bit.backend.services.impl;

import com.bit.backend.dtos.FeedbackDto;
import com.bit.backend.dtos.FeedbackStatusUpdateDto;
import com.bit.backend.entities.FeedbackEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.FeedbackMapper;
import com.bit.backend.repositories.FeedbackRepository;
import com.bit.backend.services.FeedbackServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FeedbackService implements FeedbackServiceI {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public FeedbackDto addFeedbackEntry(FeedbackDto feedbackDto) {
        FeedbackEntity entity = feedbackMapper.toFeedbackEntity(feedbackDto);
        entity.setSubmittedAt(LocalDateTime.now());
        entity.setStatus("PENDING");
        entity.setAdminRemarks(null);
        return feedbackMapper.toFeedbackDto(feedbackRepository.save(entity));
    }

    @Override
    public List<FeedbackDto> getAllFeedbackEntries() {
        return feedbackMapper.toFeedbackDtoList(feedbackRepository.findAll());
    }

    @Override
    public List<FeedbackDto> getFeedbacksBySubmittedBy(String submittedBy) {
        return feedbackMapper.toFeedbackDtoList(feedbackRepository.findBySubmittedBy(submittedBy));
    }

    @Override
    public FeedbackDto updateFeedback(long id, FeedbackDto feedbackDto) {
        FeedbackEntity existing = feedbackRepository.findById(id)
                .orElseThrow(() -> new AppException("Feedback not found", HttpStatus.NOT_FOUND));

        if (!"PENDING".equals(existing.getStatus())) {
            throw new AppException("Only pending feedback can be edited", HttpStatus.BAD_REQUEST);
        }

        existing.setRating(feedbackDto.getRating());
        existing.setFeedback(feedbackDto.getFeedback());
        existing.setAnonymous(feedbackDto.isAnonymous());

        return feedbackMapper.toFeedbackDto(feedbackRepository.save(existing));
    }

    @Override
    public FeedbackDto updateStatus(long id, FeedbackStatusUpdateDto statusUpdateDto) {
        FeedbackEntity existing = feedbackRepository.findById(id)
                .orElseThrow(() -> new AppException("Feedback not found", HttpStatus.NOT_FOUND));

        existing.setStatus(statusUpdateDto.getStatus());
        if (statusUpdateDto.getAdminRemarks() != null && !statusUpdateDto.getAdminRemarks().isBlank()) {
            existing.setAdminRemarks(statusUpdateDto.getAdminRemarks());
        }

        return feedbackMapper.toFeedbackDto(feedbackRepository.save(existing));
    }

    @Override
    public FeedbackDto deleteFeedback(long id) {
        FeedbackEntity existing = feedbackRepository.findById(id)
                .orElseThrow(() -> new AppException("Feedback not found", HttpStatus.NOT_FOUND));
        feedbackRepository.deleteById(id);
        return feedbackMapper.toFeedbackDto(existing);
    }

    @Override
    public Map<String, Object> getAnalytics() {
        List<FeedbackEntity> all = feedbackRepository.findAll();

        double avgRating = all.stream()
                .mapToInt(FeedbackEntity::getRating)
                .average()
                .orElse(0.0);

        List<Map<String, Object>> trainerRatings = new ArrayList<>();
        for (Object[] row : feedbackRepository.findTrainerRatings()) {
            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("trainerName", row[0]);
            entry.put("averageRating", Math.round(((Double) row[1]) * 10.0) / 10.0);
            entry.put("totalReviews", row[2]);
            trainerRatings.add(entry);
        }

        Map<String, Object> analytics = new LinkedHashMap<>();
        analytics.put("total", (long) all.size());
        analytics.put("pending", feedbackRepository.countByStatus("PENDING"));
        analytics.put("reviewed", feedbackRepository.countByStatus("REVIEWED"));
        analytics.put("resolved", feedbackRepository.countByStatus("RESOLVED"));
        analytics.put("averageRating", Math.round(avgRating * 10.0) / 10.0);
        analytics.put("trainerRatings", trainerRatings);

        return analytics;
    }
}
