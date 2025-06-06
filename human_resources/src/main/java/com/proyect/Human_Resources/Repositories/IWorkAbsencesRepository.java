package com.proyect.Human_Resources.Repositories;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.WorkAbsences;



public interface IWorkAbsencesRepository extends JpaRepository<WorkAbsences, Long> {
    // Custom query methods can be defined here if needed

    public ArrayList<WorkAbsences> findByEmployeeDepartmentHeadquarterCompanyNit(long nit);
}
