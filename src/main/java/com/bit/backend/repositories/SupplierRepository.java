package com.bit.backend.repositories;

import com.bit.backend.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM supplier WHERE status =  1; ")
    List<SupplierEntity> getSuppliers();
}
