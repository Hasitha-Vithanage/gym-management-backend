package com.bit.backend.dtos;

public class FeedbackStatusUpdateDto {

    private String status;
    private String adminRemarks;

    public FeedbackStatusUpdateDto() {}

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAdminRemarks() { return adminRemarks; }
    public void setAdminRemarks(String adminRemarks) { this.adminRemarks = adminRemarks; }
}
