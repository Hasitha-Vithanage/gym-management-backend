package com.bit.backend.repositories;

import com.bit.backend.entities.MemberLoginEntity;
import com.bit.backend.entities.TrainerLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLoginRepository extends JpaRepository<MemberLoginEntity, Long> {
    MemberLoginEntity findByMember(Long member);
}
