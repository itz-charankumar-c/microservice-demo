package com.demo.department.repository;

import com.demo.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for {@link Department}.
 * Provides CRUD operations out of the box.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

