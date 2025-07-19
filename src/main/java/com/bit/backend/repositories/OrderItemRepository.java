package com.bit.backend.repositories;

import com.bit.backend.entities.FormDemoEntity;
import com.bit.backend.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM ems.order_items where order_id = :id")
    OrderItemEntity findByOrderId(Long id);
}
