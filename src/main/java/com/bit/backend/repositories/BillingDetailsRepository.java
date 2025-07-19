package com.bit.backend.repositories;

import com.bit.backend.entities.BillingDetailsEntity;
import com.bit.backend.entities.FormDemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BillingDetailsRepository extends JpaRepository<BillingDetailsEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM ems.billing_details where order_id = :id")
    BillingDetailsEntity findByOrderId(Long id);
}
