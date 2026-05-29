package com.bit.backend.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trainer_request")
public class TrainerRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "status")
    private String status;

    @Column(name = "level")
    private String level;

    @Column(name = "goal")
    private String goal;

    @Column(name = "bmi_category")
    private String bmiCategory;

    @Column(name = "request_date")
    private Date requestDate;

    public TrainerRequestEntity() {}

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
