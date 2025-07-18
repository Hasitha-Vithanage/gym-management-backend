package com.bit.backend.services;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.TrainerLoginDto;

import java.util.List;

public interface TrainerLoginServiceI {

    TrainerLoginDto addTrainerLoginEntity(TrainerLoginDto trainerLoginDto);
    List<TrainerLoginDto> getTrainerLoginEntity();
}
