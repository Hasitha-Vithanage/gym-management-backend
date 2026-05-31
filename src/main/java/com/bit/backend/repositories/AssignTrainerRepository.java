package com.bit.backend.repositories;

import com.bit.backend.entities.AssignTrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssignTrainerRepository extends JpaRepository<AssignTrainerEntity, Long> {

    boolean existsByMemberId(Long memberId);

    AssignTrainerEntity findByMemberId(Long memberId);

    long countByTrainerId(Long trainerId);

    List<AssignTrainerEntity> findByTrainerId(Long trainerId);

}
