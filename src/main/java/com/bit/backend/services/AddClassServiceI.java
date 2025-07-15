package com.bit.backend.services;

import com.bit.backend.dtos.AddClassDto;

import java.util.List;

public interface AddClassServiceI {

    AddClassDto addAddClassEntity(AddClassDto addClassDto);
    List<AddClassDto> getAddClass();
    AddClassDto updateAddClass(long id, AddClassDto addClassDto);
    AddClassDto getClassById(long id);
}
