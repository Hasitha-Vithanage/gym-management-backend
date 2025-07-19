package com.bit.backend.services;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.TrainerLoginDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TrainerLoginServiceI {

    TrainerLoginDto addTrainerLoginEntity(TrainerLoginDto trainerLoginDto);
    List<TrainerLoginDto> getTrainerLoginEntity();
    TrainerLoginDto updateTrainerLoginEntity(TrainerLoginDto trainerLoginDto, long id);
}
