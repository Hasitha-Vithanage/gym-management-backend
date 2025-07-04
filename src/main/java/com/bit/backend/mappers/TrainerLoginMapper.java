package com.bit.backend.mappers;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.TrainerLoginDto;
import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.TrainerLoginEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TrainerLoginMapper {

    TrainerLoginDto toTrainerLoginDto(TrainerLoginEntity trainerLoginEntity);
    TrainerLoginEntity toTrainerLoginEntity(TrainerLoginDto trainerLoginDto);
}
