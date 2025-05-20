package com.bit.backend.services.impl;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.FeedbackDto;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.FeedbackEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.FeedbackMapper;
import com.bit.backend.repositories.FeedbackRepository;
import com.bit.backend.services.FeedbackServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        System.out.println("***********");

        FeedbackEntity feedbackEntity = feedbackMapper.toFeedbackEntity(feedbackDto);
        FeedbackEntity savedItem = feedbackRepository.save(feedbackEntity);
        FeedbackDto savedDto = feedbackMapper.toFeedbackDto(savedItem);
        return savedDto;
    }

    @Override
    public List<FeedbackDto> getAllFeedbackEntries() {
        // db operations and send data
        List<FeedbackEntity> feedbackEntityList = feedbackRepository.findAll();
        List<FeedbackDto> feedbackDtoList = feedbackMapper.toFeedbackDtoList(feedbackEntityList);
        return feedbackDtoList;
    }

    @Override
    public FeedbackDto updateFeedback(long id, FeedbackDto feedbackDto) {
        try {
            Optional<FeedbackEntity> optionalFeedbackEntity = feedbackRepository.findById(id);

            if (!optionalFeedbackEntity.isPresent()) {
                throw new AppException("Feedback Does Not Exist", HttpStatus.BAD_REQUEST);
            }

            FeedbackEntity newFeedbackEntity = feedbackMapper.toFeedbackEntity(feedbackDto);
            newFeedbackEntity.setId(id);
            FeedbackEntity feedbackEntity = feedbackRepository.save(newFeedbackEntity);
            FeedbackDto responseFeedbackDto = feedbackMapper.toFeedbackDto(feedbackEntity);
            return responseFeedbackDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FeedbackDto deleteFeedback(long id) {
        try {
            Optional<FeedbackEntity> optionalFeedbackEntity = feedbackRepository.findById(id);

            if (!optionalFeedbackEntity.isPresent()) {
                throw new AppException("Feedback Does Not Exsist", HttpStatus.BAD_REQUEST);
            }

            feedbackRepository.deleteById(id);

            FeedbackDto feedbackDto = feedbackMapper.toFeedbackDto(optionalFeedbackEntity.get());
            return feedbackDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
