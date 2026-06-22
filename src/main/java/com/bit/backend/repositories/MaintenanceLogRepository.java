package com.bit.backend.repositories;

import com.bit.backend.entities.MaintenanceLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLogEntity, Long> {
    List<MaintenanceLogEntity> findByEquipmentIdOrderByPerformedDateDesc(Long equipmentId);
}
