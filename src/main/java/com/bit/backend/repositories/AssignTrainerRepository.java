package com.bit.backend.repositories;

import com.bit.backend.entities.AssignTrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignTrainerRepository extends JpaRepository<AssignTrainerEntity, Long> {

    boolean existsByMember(String member);


}
