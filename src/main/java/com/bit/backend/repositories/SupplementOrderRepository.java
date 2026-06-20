package com.bit.backend.repositories;

import com.bit.backend.entities.SupplementOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplementOrderRepository extends JpaRepository<SupplementOrderEntity, Long> {

    List<SupplementOrderEntity> findAllByIsDeletedFalseOrderByOrderDateDesc();
    List<SupplementOrderEntity> findAllByMemberUsernameAndIsDeletedFalseOrderByOrderDateDesc(String memberUsername);
}
