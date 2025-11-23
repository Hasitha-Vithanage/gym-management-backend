package com.bit.backend.repositories;

import com.bit.backend.entities.MemberEntity;
import com.bit.backend.entities.MembershipCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipCategoryRepository extends JpaRepository<MembershipCategoryEntity, Long> {

    boolean existsByCategoryName(String categoryName);
    List<MembershipCategoryEntity> findByCategoryName(String categoryName);
}
