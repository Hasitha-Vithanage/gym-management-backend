package com.bit.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserWorkoutAssignmentDto {

    private Long id;
    private Long userId;
    private Long templateId;
    private LocalDate startDate;
    private LocalDate endDate;       // null = indefinite
    private String status;
    private LocalDateTime createdAt;

    // Populated by the service — full template details for the frontend
    private WorkoutTemplateDto template;

    public UserWorkoutAssignmentDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getTemplateId() { return templateId; }
    public void setTemplateId(Long templateId) { this.templateId = templateId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public WorkoutTemplateDto getTemplate() { return template; }
    public void setTemplate(WorkoutTemplateDto template) { this.template = template; }
}
