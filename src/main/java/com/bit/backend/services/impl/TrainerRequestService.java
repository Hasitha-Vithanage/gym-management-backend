package com.bit.backend.services.impl;

import com.bit.backend.dtos.TrainerRequestDto;
import com.bit.backend.entities.TrainerRequestEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.repositories.TrainerRequestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerRequestService {

    private final TrainerRequestRepository repository;

    public TrainerRequestService(TrainerRequestRepository repository) {
        this.repository = repository;
    }

    public TrainerRequestDto createRequest(TrainerRequestDto dto) {
        Optional<TrainerRequestEntity> existing = repository.findByMemberId(dto.getMemberId());
        if (existing.isPresent() && "PENDING".equals(existing.get().getStatus())) {
            throw new AppException("A pending trainer request already exists for this member", HttpStatus.CONFLICT);
        }

        TrainerRequestEntity entity = new TrainerRequestEntity();
        entity.setMemberId(dto.getMemberId());
        entity.setMemberName(dto.getMemberName());
        entity.setStatus("PENDING");
        entity.setLevel(dto.getLevel());
        entity.setGoal(dto.getGoal());
        entity.setBmiCategory(dto.getBmiCategory());
        entity.setRequestDate(new Date());

        return toDto(repository.save(entity));
    }

    public TrainerRequestDto getByMemberId(Long memberId) {
        return repository.findByMemberId(memberId).map(this::toDto).orElse(null);
    }

    public TrainerRequestDto updateStatus(Long memberId, String status) {
        TrainerRequestEntity entity = repository.findByMemberId(memberId)
                .orElseThrow(() -> new AppException("Trainer request not found", HttpStatus.NOT_FOUND));
        entity.setStatus(status);
        return toDto(repository.save(entity));
    }

    public List<TrainerRequestDto> getAllRequests() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private TrainerRequestDto toDto(TrainerRequestEntity e) {
        TrainerRequestDto dto = new TrainerRequestDto();
        dto.setId(e.getId());
        dto.setMemberId(e.getMemberId());
        dto.setMemberName(e.getMemberName());
        dto.setStatus(e.getStatus());
        dto.setLevel(e.getLevel());
        dto.setGoal(e.getGoal());
        dto.setBmiCategory(e.getBmiCategory());
        dto.setRequestDate(e.getRequestDate());
        return dto;
    }
}
