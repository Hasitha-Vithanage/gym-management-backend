package com.bit.backend.services.impl;

import com.bit.backend.dtos.MealPlanTemplateDto;
import com.bit.backend.dtos.SuggestedMealPlanDto;
import com.bit.backend.dtos.UserMealPlanAssignmentDto;
import com.bit.backend.entities.MealPlanTemplateEntity;
import com.bit.backend.entities.MemberNutritionProfileEntity;
import com.bit.backend.entities.ProgressTrackingEntity;
import com.bit.backend.entities.UserMealPlanAssignmentEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MealPlanTemplateMapper;
import com.bit.backend.repositories.MealPlanTemplateRepository;
import com.bit.backend.repositories.MemberNutritionProfileRepository;
import com.bit.backend.repositories.ProgressTrackingRepository;
import com.bit.backend.repositories.TemplateMealItemRepository;
import com.bit.backend.repositories.UserMealPlanAssignmentRepository;
import com.bit.backend.services.UserMealPlanAssignmentServiceI;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserMealPlanAssignmentService implements UserMealPlanAssignmentServiceI {

    private final UserMealPlanAssignmentRepository assignmentRepository;
    private final MealPlanTemplateRepository templateRepository;
    private final MealPlanTemplateMapper templateMapper;
    private final TemplateMealItemRepository mealItemRepository;
    private final MemberNutritionProfileRepository profileRepository;
    private final ProgressTrackingRepository progressRepository;

    public UserMealPlanAssignmentService(
            UserMealPlanAssignmentRepository assignmentRepository,
            MealPlanTemplateRepository templateRepository,
            MealPlanTemplateMapper templateMapper,
            TemplateMealItemRepository mealItemRepository,
            MemberNutritionProfileRepository profileRepository,
            ProgressTrackingRepository progressRepository) {
        this.assignmentRepository = assignmentRepository;
        this.templateRepository = templateRepository;
        this.templateMapper = templateMapper;
        this.mealItemRepository = mealItemRepository;
        this.profileRepository = profileRepository;
        this.progressRepository = progressRepository;
    }

    @Override
    @Transactional
    public UserMealPlanAssignmentDto createAssignment(String userId, Long templateId, String assignedBy, Integer durationWeeks) {
        try {
            // Mark any existing Active assignment as Replaced
            List<UserMealPlanAssignmentEntity> active =
                    assignmentRepository.findByUserIdAndStatus(userId, "Active");
            active.forEach(a -> a.setStatus("Replaced"));
            assignmentRepository.saveAll(active);

            LocalDate startDate = LocalDate.now();
            LocalDate endDate = (durationWeeks != null && durationWeeks > 0)
                    ? startDate.plusWeeks(durationWeeks)
                    : null;

            UserMealPlanAssignmentEntity entity = new UserMealPlanAssignmentEntity();
            entity.setUserId(userId);
            entity.setTemplateId(templateId);
            entity.setAssignedBy(assignedBy);
            entity.setStartDate(startDate);
            entity.setEndDate(endDate);
            entity.setStatus("Active");

            return toDto(assignmentRepository.save(entity));
        } catch (Exception e) {
            throw new AppException("Failed to create meal plan assignment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserMealPlanAssignmentDto getActiveAssignment(String userId) {
        try {
            Optional<UserMealPlanAssignmentEntity> opt =
                    assignmentRepository.findTopByUserIdAndStatusOrderByCreatedAtDesc(userId, "Active");

            if (opt.isEmpty()) return null;

            UserMealPlanAssignmentEntity entity = opt.get();

            // Auto-expire if duration has passed
            if (entity.getEndDate() != null && entity.getEndDate().isBefore(LocalDate.now())) {
                entity.setStatus("Expired");
                assignmentRepository.save(entity);
                return null;
            }

            return toDto(entity);
        } catch (Exception e) {
            throw new AppException("Failed to load active meal plan assignment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<UserMealPlanAssignmentDto> getAllAssignmentsForMember(String userId) {
        try {
            return assignmentRepository.findByUserIdOrderByCreatedAtDesc(userId)
                    .stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new AppException("Failed to load assignments: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Auto-suggest algorithm:
     *  Round 1 — Goal + BMI exact match  → ranked by dietary preference overlap
     *  Round 2 — Goal match only          → ranked by dietary preference overlap
     *  Round 3 — All active templates     → ranked by dietary preference overlap
     *
     * Within each round, each template is scored +2 per matching dietary preference tag.
     */
    @Override
    public List<SuggestedMealPlanDto> suggestTemplates(String userId) {
        try {
            // 1. Fetch nutrition profile for goal + dietary preferences
            Optional<MemberNutritionProfileEntity> profileOpt =
                    profileRepository.findTopByUserIdOrderByIdDesc(userId);

            String goal = profileOpt.map(MemberNutritionProfileEntity::getFitnessGoal).orElse(null);
            List<String> memberPreferences = profileOpt
                    .map(MemberNutritionProfileEntity::getDietaryPreferences)
                    .orElse(Collections.emptyList());

            // 2. Fetch latest BMI and derive category
            String bmiCategory = progressRepository.findTopByUserNameOrderByDateDesc(userId)
                    .map(p -> toBmiCategory(p.getBmi()))
                    .orElse(null);

            // 3. Matching in rounds — first non-empty round wins
            List<MealPlanTemplateEntity> candidates = Collections.emptyList();
            String matchRound;
            String baseReason;

            if (goal != null && bmiCategory != null) {
                candidates = templateRepository.findActiveByGoalAndBmi(goal, bmiCategory);
                if (!candidates.isEmpty()) {
                    matchRound = "Exact";
                    baseReason = "Goal (" + goal + ") and BMI (" + bmiCategory + ") matched";
                } else {
                    candidates = templateRepository.findActiveByGoal(goal);
                    if (!candidates.isEmpty()) {
                        matchRound = "GoalOnly";
                        baseReason = "Goal (" + goal + ") matched — no BMI-specific templates found";
                    } else {
                        candidates = templateRepository.findAllByIsDeletedFalse();
                        matchRound = "All";
                        baseReason = "No goal or BMI match — showing all available templates";
                    }
                }
            } else if (goal != null) {
                candidates = templateRepository.findActiveByGoal(goal);
                if (!candidates.isEmpty()) {
                    matchRound = "GoalOnly";
                    baseReason = "Goal (" + goal + ") matched — no BMI data available";
                } else {
                    candidates = templateRepository.findAllByIsDeletedFalse();
                    matchRound = "All";
                    baseReason = "No goal match — showing all available templates";
                }
            } else {
                candidates = templateRepository.findAllByIsDeletedFalse();
                matchRound = "All";
                baseReason = "No nutrition profile submitted — showing all available templates";
            }

            // 4. Score each candidate by dietary preference overlap
            final String round = matchRound;
            final String reason = baseReason;
            final List<String> prefs = memberPreferences;

            List<SuggestedMealPlanDto> suggestions = new ArrayList<>();
            for (MealPlanTemplateEntity t : candidates) {
                MealPlanTemplateDto dto = templateMapper.toDto(t);
                dto.setFoodItemCount(mealItemRepository.countByTemplateId(t.getId()));

                int score = 0;
                List<String> templateTags = t.getDietaryTags() != null ? t.getDietaryTags() : Collections.emptyList();
                for (String pref : prefs) {
                    if (templateTags.stream().anyMatch(tag -> tag.equalsIgnoreCase(pref))) {
                        score += 2;
                    }
                }

                suggestions.add(new SuggestedMealPlanDto(dto, round, reason, score));
            }

            // 5. Sort by score descending (highest match first)
            suggestions.sort((a, b) -> Integer.compare(b.getMatchScore(), a.getMatchScore()));
            return suggestions;

        } catch (Exception e) {
            throw new AppException("Failed to generate suggestions: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ──────────────────────────────────────────────
    // Private helpers
    // ──────────────────────────────────────────────

    private UserMealPlanAssignmentDto toDto(UserMealPlanAssignmentEntity entity) {
        UserMealPlanAssignmentDto dto = new UserMealPlanAssignmentDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setTemplateId(entity.getTemplateId());
        dto.setAssignedBy(entity.getAssignedBy());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());

        templateRepository.findById(entity.getTemplateId()).ifPresent(t -> {
            MealPlanTemplateDto templateDto = templateMapper.toDto(t);
            templateDto.setFoodItemCount(mealItemRepository.countByTemplateId(t.getId()));
            dto.setTemplate(templateDto);
        });

        return dto;
    }

    private String toBmiCategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25.0) return "Normal";
        if (bmi < 30.0) return "Overweight";
        return "Obese";
    }
}
