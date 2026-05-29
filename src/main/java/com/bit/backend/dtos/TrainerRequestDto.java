package com.bit.backend.dtos;

import java.util.Date;

public class TrainerRequestDto {

    private Long id;
    private Long memberId;
    private String memberName;
    private String status;
    private String level;
    private String goal;
    private String bmiCategory;
    private Date requestDate;

    public TrainerRequestDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }

    public String getBmiCategory() { return bmiCategory; }
    public void setBmiCategory(String bmiCategory) { this.bmiCategory = bmiCategory; }

    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }
}
