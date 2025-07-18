package com.bit.backend.services;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.ProgressTrackingDto;

import java.util.List;
import java.util.Map;

public interface ProgressTrackingServiceI {
    ProgressTrackingDto addProgressTrackingEntity(ProgressTrackingDto progressTrackingDto);


    List<Map<String, Object>> getWeightOverTimeByUser(String userName);

    List<ProgressTrackingDto> getProgressData();
}
