package com.proyect.Human_Resources.Repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {
    // Custom query methods can be defined here if needed

    public ArrayList<Department> findByHeadquarterCompanyNit(long nit); // Method to find departments by company Nit

    public Optional<Department> findByHeadquarterId(Long id);
}
