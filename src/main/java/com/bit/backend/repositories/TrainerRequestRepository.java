package com.bit.backend.repositories;

import com.bit.backend.entities.TrainerRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRequestRepository extends JpaRepository<TrainerRequestEntity, Long> {
    Optional<TrainerRequestEntity> findByMemberId(Long memberId);
}
