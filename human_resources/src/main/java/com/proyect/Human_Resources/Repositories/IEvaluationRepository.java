package com.proyect.Human_Resources.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Evaluation;

public interface IEvaluationRepository extends JpaRepository<Evaluation, Long> {
    // Custom query methods can be defined here if needed

    public ArrayList<Evaluation> findByEmployeeDepartmentHeadquarterCompanyNit(long nit); // Finds evaluations by the company's NIT
}
