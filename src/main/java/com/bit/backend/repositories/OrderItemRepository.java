package com.bit.backend.repositories;

import com.bit.backend.entities.FormDemoEntity;
import com.bit.backend.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
