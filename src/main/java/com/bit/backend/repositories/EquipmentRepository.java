package com.bit.backend.repositories;

import com.bit.backend.entities.EquipmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {
    long countByStatus(String status);

    @Query("SELECT e FROM EquipmentEntity e WHERE e.nextMaintenance IS NOT NULL AND e.nextMaintenance < CURRENT_DATE AND e.status = 'ACTIVE'")
    List<EquipmentEntity> findOverdue();

    long countBySupplier(Long supplier);
}
