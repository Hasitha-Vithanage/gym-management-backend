package com.bit.backend.repositories;

import com.bit.backend.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM ems.orders WHERE total_cost > 6000")
    List<OrderEntity> getOrdersOverLimit();

//    @Query(nativeQuery = true, value = "SELECT * FROM ems.orders where order_id = :id")
//    OrderEntity findByOrderId(Long id);
}
