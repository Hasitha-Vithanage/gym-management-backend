package com.bit.backend.dtos;

import java.time.LocalDate;
import java.util.List;

public class WorkoutSessionSummaryDto {

    private Integer totalSessionsCompleted;
    private Integer sessionsThisWeek;
    private Integer targetSessionsPerWeek;
    private Integer currentProgramWeek;
    private Integer totalProgramWeeks;
    private LocalDate lastSessionDate;
    private Integer daysSinceLastSession;
    private List<Integer> completedWorkoutDays;
    private Integer lastWorkoutDay;

    public WorkoutSessionSummaryDto() {}

    public Integer getTotalSessionsCompleted() { return totalSessionsCompleted; }
    public void setTotalSessionsCompleted(Integer totalSessionsCompleted) { this.totalSessionsCompleted = totalSessionsCompleted; }

    public Integer getSessionsThisWeek() { return sessionsThisWeek; }
    public void setSessionsThisWeek(Integer sessionsThisWeek) { this.sessionsThisWeek = sessionsThisWeek; }

    public Integer getTargetSessionsPerWeek() { return targetSessionsPerWeek; }
    public void setTargetSessionsPerWeek(Integer targetSessionsPerWeek) { this.targetSessionsPerWeek = targetSessionsPerWeek; }

    public Integer getCurrentProgramWeek() { return currentProgramWeek; }
    public void setCurrentProgramWeek(Integer currentProgramWeek) { this.currentProgramWeek = currentProgramWeek; }

    public Integer getTotalProgramWeeks() { return totalProgramWeeks; }
    public void setTotalProgramWeeks(Integer totalProgramWeeks) { this.totalProgramWeeks = totalProgramWeeks; }

    public LocalDate getLastSessionDate() { return lastSessionDate; }
    public void setLastSessionDate(LocalDate lastSessionDate) { this.lastSessionDate = lastSessionDate; }

    public Integer getDaysSinceLastSession() { return daysSinceLastSession; }
    public void setDaysSinceLastSession(Integer daysSinceLastSession) { this.daysSinceLastSession = daysSinceLastSession; }

    public List<Integer> getCompletedWorkoutDays() { return completedWorkoutDays; }
    public void setCompletedWorkoutDays(List<Integer> completedWorkoutDays) { this.completedWorkoutDays = completedWorkoutDays; }

    public Integer getLastWorkoutDay() { return lastWorkoutDay; }
    public void setLastWorkoutDay(Integer lastWorkoutDay) { this.lastWorkoutDay = lastWorkoutDay; }
}
