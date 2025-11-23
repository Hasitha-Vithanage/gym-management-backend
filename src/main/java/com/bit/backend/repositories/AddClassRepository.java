package com.bit.backend.repositories;

import com.bit.backend.entities.AddClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddClassRepository extends JpaRepository<AddClassEntity, Long> {
}
