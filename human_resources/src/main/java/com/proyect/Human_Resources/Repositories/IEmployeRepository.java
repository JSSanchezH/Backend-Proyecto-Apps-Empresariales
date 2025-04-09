package com.proyect.Human_Resources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Employee;

public interface IEmployeRepository extends JpaRepository<Employee, Long> {
    // Custom query methods can be defined here if needed
}
