package com.bit.backend.mappers;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.MemberEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AssignTrainerMapper {

    AssignTrainerDto toAssignTrainerDto(AssignTrainerEntity assignTrainerEntity);
    AssignTrainerEntity toAssignTrainerEntity(AssignTrainerDto assignTrainerDto);
    List<AssignTrainerDto> toAssignTrainerDto(List<AssignTrainerEntity> assignTrainerEntities);
}
