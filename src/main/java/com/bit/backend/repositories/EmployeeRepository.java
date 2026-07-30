package com.bit.backend.repositories;

import com.bit.backend.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM employee WHERE job_title = 'Trainer' AND (is_deleted IS NULL OR is_deleted = false)")
    List<EmployeeEntity> getTrainers();

    @Query(nativeQuery = true, value = "SELECT * FROM ems.employee WHERE first_name = ?1 AND job_title = 'Trainer'")
    EmployeeEntity findByFirstName(String trainerName);

    boolean existsByNic(String nic);

    boolean existsByNicAndIdNot(String nic, Long id);
}
