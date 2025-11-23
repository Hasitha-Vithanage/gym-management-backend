package com.bit.backend.mappers;

import com.bit.backend.dtos.AddClassDto;
import com.bit.backend.entities.AddClassEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AddClassMapper {

    AddClassDto toAddClassDto(AddClassEntity addClassEntity);
    AddClassEntity toAddClassEntity(AddClassDto addClassDto);
    List<AddClassDto> toAddClassDto(List<AddClassEntity> addClassEntities);
}
