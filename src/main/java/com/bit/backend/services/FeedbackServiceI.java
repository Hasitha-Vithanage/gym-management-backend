package com.bit.backend.services;

import com.bit.backend.dtos.FeedbackDto;
import com.bit.backend.dtos.FeedbackStatusUpdateDto;

import java.util.List;
import java.util.Map;

public interface FeedbackServiceI {

    FeedbackDto addFeedbackEntry(FeedbackDto feedbackDto);
    List<FeedbackDto> getAllFeedbackEntries();
    List<FeedbackDto> getFeedbacksBySubmittedBy(String submittedBy);
    FeedbackDto updateFeedback(long id, FeedbackDto feedbackDto);
    FeedbackDto updateStatus(long id, FeedbackStatusUpdateDto statusUpdateDto);
    FeedbackDto deleteFeedback(long id);
    Map<String, Object> getAnalytics();
}
