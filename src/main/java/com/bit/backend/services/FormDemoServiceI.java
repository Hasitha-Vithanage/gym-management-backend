package com.bit.backend.services;

import com.bit.backend.dtos.FormDemoDto;

import java.util.List;

public interface FormDemoServiceI {

    FormDemoDto addFormDemoEntry(FormDemoDto formDemoDto);
    List<FormDemoDto> getAllFormDemoEntries();

}
