package com.bit.backend.dtos;

import java.time.LocalDate;

public class MemberProgressSummaryDto {

    private Long userId;
    private String memberName;
    private String programName;
    private Integer daysPerWeek;
    private LocalDate programStartDate;
    private Integer currentProgramWeek;
    private Integer totalProgramWeeks;
    private Integer sessionsThisWeek;
    private LocalDate lastSessionDate;
    private Integer daysSinceLastSession;
    private String activityStatus; // ACTIVE, INACTIVE, DROPPED_OFF

    public MemberProgressSummaryDto() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public String getProgramName() { return programName; }
    public void setProgramName(String programName) { this.programName = programName; }

    public Integer getDaysPerWeek() { return daysPerWeek; }
    public void setDaysPerWeek(Integer daysPerWeek) { this.daysPerWeek = daysPerWeek; }

    public LocalDate getProgramStartDate() { return programStartDate; }
    public void setProgramStartDate(LocalDate programStartDate) { this.programStartDate = programStartDate; }

    public Integer getCurrentProgramWeek() { return currentProgramWeek; }
    public void setCurrentProgramWeek(Integer currentProgramWeek) { this.currentProgramWeek = currentProgramWeek; }

    public Integer getTotalProgramWeeks() { return totalProgramWeeks; }
    public void setTotalProgramWeeks(Integer totalProgramWeeks) { this.totalProgramWeeks = totalProgramWeeks; }

    public Integer getSessionsThisWeek() { return sessionsThisWeek; }
    public void setSessionsThisWeek(Integer sessionsThisWeek) { this.sessionsThisWeek = sessionsThisWeek; }

    public LocalDate getLastSessionDate() { return lastSessionDate; }
    public void setLastSessionDate(LocalDate lastSessionDate) { this.lastSessionDate = lastSessionDate; }

    public Integer getDaysSinceLastSession() { return daysSinceLastSession; }
    public void setDaysSinceLastSession(Integer daysSinceLastSession) { this.daysSinceLastSession = daysSinceLastSession; }

    public String getActivityStatus() { return activityStatus; }
    public void setActivityStatus(String activityStatus) { this.activityStatus = activityStatus; }
}
