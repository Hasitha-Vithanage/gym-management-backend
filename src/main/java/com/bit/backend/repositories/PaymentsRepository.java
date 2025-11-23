package com.bit.backend.repositories;

import com.bit.backend.entities.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<PaymentsEntity, Long> {

    boolean existsByMember(String categoryName);
}
