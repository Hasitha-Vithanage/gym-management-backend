package com.bit.backend.mappers;

import com.bit.backend.dtos.BookClassDto;
import com.bit.backend.entities.BookClassEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BookClassMapper {

    BookClassDto toBookClassDto(BookClassEntity bookClassEntity);
    BookClassEntity toBookClassEntity(BookClassDto bookClassDto);
    List<BookClassDto> toBookClassDto(List<BookClassEntity> bookClassEntities);
}
