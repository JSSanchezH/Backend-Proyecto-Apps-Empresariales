package com.proyect.Human_Resources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {
    // Custom query methods can be defined here if needed
}
