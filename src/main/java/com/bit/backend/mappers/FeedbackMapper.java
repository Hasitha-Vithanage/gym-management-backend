package com.bit.backend.mappers;

import com.bit.backend.dtos.FeedbackDto;
import com.bit.backend.entities.FeedbackEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface FeedbackMapper {

    FeedbackDto toFeedbackDto(FeedbackEntity feedbackEntity);
    FeedbackEntity toFeedbackEntity(FeedbackDto feedbackDto);
    List<FeedbackDto> toFeedbackDtoList(List<FeedbackEntity> feedbackEntityList);

}
