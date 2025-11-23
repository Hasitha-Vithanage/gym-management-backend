package com.bit.backend.services;

import com.bit.backend.dtos.FeedbackDto;

import java.util.List;

public interface FeedbackServiceI {

    FeedbackDto addFeedbackEntry(FeedbackDto feedbackDto);
    List<FeedbackDto> getAllFeedbackEntries();
    FeedbackDto updateFeedback(long id, FeedbackDto feedbackDto);

    FeedbackDto deleteFeedback(long id);

    List<FeedbackDto> getFeedbacksByUserName(String userName);
}
