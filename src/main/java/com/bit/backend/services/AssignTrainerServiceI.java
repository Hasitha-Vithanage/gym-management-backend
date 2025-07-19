package com.bit.backend.services;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.EquipmentDto;
import com.bit.backend.entities.User;

import java.util.List;

public interface AssignTrainerServiceI {

    AssignTrainerDto addAssignTrainerEntity(AssignTrainerDto assignTrainerDto);
    AssignTrainerDto updateAssignTrainer(long id, AssignTrainerDto assignTrainerDto);
    List<AssignTrainerDto> getAssignTrainer();
    AssignTrainerDto deleteAssignTrainer(long id);
    AssignTrainerDto getAssignTrainerByMember(String member);

    User getTrainerByMember(String id);
}
