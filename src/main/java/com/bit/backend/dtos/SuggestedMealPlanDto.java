package com.bit.backend.dtos;

public class SuggestedMealPlanDto {

    private MealPlanTemplateDto template;

    // "Exact"    = Goal + BMI both matched
    // "GoalOnly" = Goal matched, BMI criteria relaxed
    // "All"      = No criteria matched, showing all templates
    private String matchRound;

    // Human-readable explanation shown in the trainer UI
    private String matchReason;

    // Higher = better match — used for sorting within a round
    private int matchScore;

    public SuggestedMealPlanDto() {
    }

    public SuggestedMealPlanDto(MealPlanTemplateDto template, String matchRound, String matchReason, int matchScore) {
        this.template = template;
        this.matchRound = matchRound;
        this.matchReason = matchReason;
        this.matchScore = matchScore;
    }

    public MealPlanTemplateDto getTemplate() { return template; }
    public void setTemplate(MealPlanTemplateDto template) { this.template = template; }

    public String getMatchRound() { return matchRound; }
    public void setMatchRound(String matchRound) { this.matchRound = matchRound; }

    public String getMatchReason() { return matchReason; }
    public void setMatchReason(String matchReason) { this.matchReason = matchReason; }

    public int getMatchScore() { return matchScore; }
    public void setMatchScore(int matchScore) { this.matchScore = matchScore; }
}
