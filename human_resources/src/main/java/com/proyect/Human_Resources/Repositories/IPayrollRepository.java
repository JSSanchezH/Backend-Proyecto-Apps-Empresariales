package com.proyect.Human_Resources.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Payroll;

public interface IPayrollRepository extends JpaRepository<Payroll, Long> {
    // Custom query methods can be defined here if needed

    public ArrayList<Payroll> findByEmployeeDepartmentHeadquarterCompanyNit(long nit); // Find payrolls by company NIT
}
