package com.bit.backend.repositories;

import com.bit.backend.entities.SupplementProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplementProductRepository extends JpaRepository<SupplementProductEntity, Long> {

    List<SupplementProductEntity> findAllByIsDeletedFalse();
    List<SupplementProductEntity> findAllByIsDeletedFalseAndIsActiveTrue();
}
