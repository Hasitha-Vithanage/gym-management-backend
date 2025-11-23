package com.bit.backend.repositories;

import com.bit.backend.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM supplier WHERE status = 1 AND id NOT IN ( SELECT supplier_id FROM supplier_equipment_types WHERE equipment_type = 'supplements'); ")
    List<SupplierEntity> getSuppliers();

    @Query(nativeQuery = true, value = "SELECT * FROM supplier WHERE status = 1 AND id IN ( SELECT supplier_id FROM supplier_equipment_types WHERE equipment_type = 'supplements'); ")
    List<SupplierEntity> getSupplementSuppliers();
}
