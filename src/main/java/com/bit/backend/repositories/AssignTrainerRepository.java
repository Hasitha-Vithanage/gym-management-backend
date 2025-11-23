package com.bit.backend.repositories;

import com.bit.backend.entities.AssignTrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssignTrainerRepository extends JpaRepository<AssignTrainerEntity, Long> {

    boolean existsByMember(String member);

    @Query(value = "SELECT * FROM ems.assign_trainer WHERE member = ?1", nativeQuery = true)
    AssignTrainerEntity findByMember(String member);


//    AssignTrainerEntity findByTrainer(String firstName);

}
