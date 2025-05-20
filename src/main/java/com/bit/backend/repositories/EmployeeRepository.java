package com.bit.backend.repositories;

import com.bit.backend.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM employee WHERE job_title = \"Trainer\"")
    List<EmployeeEntity> getTrainers();
}
