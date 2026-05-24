package com.bit.backend.services;

import com.bit.backend.dtos.UserWorkoutAssignmentDto;

public interface UserWorkoutAssignmentServiceI {

    /**
     * Marks any existing Active assignment for the user as Replaced,
     * then creates and returns a new Active assignment.
     */
    UserWorkoutAssignmentDto createAssignment(Long userId, Long templateId, Integer programLengthWeeks);

    /**
     * Returns the current Active assignment for the user (with nested template),
     * or null if none exists. Automatically expires assignments whose endDate has passed.
     */
    UserWorkoutAssignmentDto getActiveAssignment(Long userId);
}
