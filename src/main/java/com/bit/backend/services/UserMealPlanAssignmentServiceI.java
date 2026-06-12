package com.bit.backend.services;

import com.bit.backend.dtos.SuggestedMealPlanDto;
import com.bit.backend.dtos.UserMealPlanAssignmentDto;

import java.util.List;

public interface UserMealPlanAssignmentServiceI {

    UserMealPlanAssignmentDto createAssignment(String userId, Long templateId, String assignedBy, Integer durationWeeks);
    UserMealPlanAssignmentDto getActiveAssignment(String userId);
    List<UserMealPlanAssignmentDto> getAllAssignmentsForMember(String userId);
    List<SuggestedMealPlanDto> suggestTemplates(String userId);
}
