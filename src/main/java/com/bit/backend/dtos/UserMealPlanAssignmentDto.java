package com.bit.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserMealPlanAssignmentDto {

    private Long id;
    private String userId;
    private Long templateId;
    private String assignedBy;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime createdAt;

    // Populated by the service — full template details for the frontend
    private MealPlanTemplateDto template;

    public UserMealPlanAssignmentDto() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Long getTemplateId() { return templateId; }
    public void setTemplateId(Long templateId) { this.templateId = templateId; }

    public String getAssignedBy() { return assignedBy; }
    public void setAssignedBy(String assignedBy) { this.assignedBy = assignedBy; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public MealPlanTemplateDto getTemplate() { return template; }
    public void setTemplate(MealPlanTemplateDto template) { this.template = template; }
}
