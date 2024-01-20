package com.example.firstspring.repository;

import com.example.firstspring.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * *repository is an interface that always extends the JpaRepository while,
 * specifying the entity and data type
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
