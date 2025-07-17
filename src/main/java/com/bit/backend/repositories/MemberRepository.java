package com.bit.backend.repositories;

import com.bit.backend.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    List<MemberEntity> findByFirstName(String member);

    @Query("SELECT COUNT(m) FROM MemberEntity m WHERE MONTH(m.joinedDate) = MONTH(CURRENT_DATE) AND YEAR(m.joinedDate) = YEAR(CURRENT_DATE)")
    Integer countNewMembersInCurrentMonth();

}
