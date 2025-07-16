package com.bit.backend.repositories;

import com.bit.backend.entities.MembershipCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipCategoryRepository extends JpaRepository<MembershipCategoryEntity, Long> {

    boolean existsByCategoryName(String categoryName);
}
