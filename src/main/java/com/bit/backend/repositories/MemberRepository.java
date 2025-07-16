package com.bit.backend.repositories;

import com.bit.backend.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    List<MemberEntity> findByFirstName(String member);
}
