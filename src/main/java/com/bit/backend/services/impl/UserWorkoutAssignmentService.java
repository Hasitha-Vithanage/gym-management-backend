package com.bit.backend.services.impl;

import com.bit.backend.dtos.UserWorkoutAssignmentDto;
import com.bit.backend.dtos.WorkoutTemplateDto;
import com.bit.backend.entities.UserWorkoutAssignmentEntity;
import com.bit.backend.entities.WorkoutTemplateEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.WorkoutTemplateMapper;
import com.bit.backend.repositories.TemplateExerciseRepository;
import com.bit.backend.repositories.UserWorkoutAssignmentRepository;
import com.bit.backend.repositories.WorkoutTemplateRepository;
import com.bit.backend.services.UserWorkoutAssignmentServiceI;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserWorkoutAssignmentService implements UserWorkoutAssignmentServiceI {

    private final UserWorkoutAssignmentRepository assignmentRepository;
    private final WorkoutTemplateRepository templateRepository;
    private final WorkoutTemplateMapper templateMapper;
    private final TemplateExerciseRepository templateExerciseRepository;

    public UserWorkoutAssignmentService(
            UserWorkoutAssignmentRepository assignmentRepository,
            WorkoutTemplateRepository templateRepository,
            WorkoutTemplateMapper templateMapper,
            TemplateExerciseRepository templateExerciseRepository) {
        this.assignmentRepository = assignmentRepository;
        this.templateRepository = templateRepository;
        this.templateMapper = templateMapper;
        this.templateExerciseRepository = templateExerciseRepository;
    }

    @Override
    @Transactional
    public UserWorkoutAssignmentDto createAssignment(Long userId, Long templateId, Integer programLengthWeeks) {
        try {
            // Mark all current Active assignments for this user as Replaced
            List<UserWorkoutAssignmentEntity> active = assignmentRepository.findByUserIdAndStatus(userId, "Active");
            active.forEach(a -> a.setStatus("Replaced"));
            assignmentRepository.saveAll(active);

            // Compute endDate — null when programLengthWeeks is not set
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = (programLengthWeeks != null && programLengthWeeks > 0)
                    ? startDate.plusWeeks(programLengthWeeks)
                    : null;

            UserWorkoutAssignmentEntity entity = new UserWorkoutAssignmentEntity();
            entity.setUserId(userId);
            entity.setTemplateId(templateId);
            entity.setStartDate(startDate);
            entity.setEndDate(endDate);
            entity.setStatus("Active");

            UserWorkoutAssignmentEntity saved = assignmentRepository.save(entity);
            return toDto(saved);
        } catch (Exception e) {
            throw new AppException("Failed to create workout assignment: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserWorkoutAssignmentDto getActiveAssignment(Long userId) {
        try {
            Optional<UserWorkoutAssignmentEntity> opt =
                    assignmentRepository.findTopByUserIdAndStatusOrderByCreatedAtDesc(userId, "Active");

            if (opt.isEmpty()) return null;

            UserWorkoutAssignmentEntity entity = opt.get();

            // Auto-expire if endDate has passed
            if (entity.getEndDate() != null && entity.getEndDate().isBefore(LocalDate.now())) {
                entity.setStatus("Expired");
                assignmentRepository.save(entity);
                return null;
            }

            return toDto(entity);
        } catch (Exception e) {
            throw new AppException("Failed to load active assignment: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private UserWorkoutAssignmentDto toDto(UserWorkoutAssignmentEntity entity) {
        UserWorkoutAssignmentDto dto = new UserWorkoutAssignmentDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setTemplateId(entity.getTemplateId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());

        // Attach full template details
        templateRepository.findById(entity.getTemplateId()).ifPresent(t -> {
            WorkoutTemplateDto templateDto = templateMapper.toWorkoutTemplateDto(t);
            templateDto.setExerciseCount(templateExerciseRepository.countByTemplateId(t.getId()));
            dto.setTemplate(templateDto);
        });

        return dto;
    }
}
