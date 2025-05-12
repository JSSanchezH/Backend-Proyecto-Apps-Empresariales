package com.proyect.Human_Resources.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Employee;

public interface IEmployeRepository extends JpaRepository<Employee, Long> {
    // Custom query methods can be defined here if needed

    //Find employees by company id

    public ArrayList<Employee> findByDepartmentHeadquarterCompanyNit(long nit); // Method to find employees by company Nit
}
