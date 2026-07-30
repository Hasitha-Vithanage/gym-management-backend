package com.bit.backend.repositories;

import com.bit.backend.entities.SupplementOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface SupplementOrderRepository extends JpaRepository<SupplementOrderEntity, Long> {

    List<SupplementOrderEntity> findAllByIsDeletedFalseOrderByOrderDateDesc();
    List<SupplementOrderEntity> findAllByMemberUsernameAndIsDeletedFalseOrderByOrderDateDesc(String memberUsername);

    @Query(nativeQuery = true, value = "select DATE_FORMAT(order_date, '%Y-%m') as month, count(*) as cnt " +
            "from ems.supplement_order where status = 'COMPLETED' and is_deleted = false group by month order by month")
    List<Map<String, Object>> getSupplementOrderCountByMonth();

    @Query(nativeQuery = true, value = "select DATE_FORMAT(order_date, '%Y-%m') as month, sum(total_amount) as cnt " +
            "from ems.supplement_order where status = 'COMPLETED' and is_deleted = false group by month order by month")
    List<Map<String, Object>> getSupplementOrderIncomeByMonth();
}
