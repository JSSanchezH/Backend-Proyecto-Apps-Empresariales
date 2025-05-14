package com.proyect.Human_Resources.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Headquarter;

public interface IHeadquarterRepository extends JpaRepository<Headquarter, Long> {
    // Custom query methods can be defined here if needed

    public ArrayList<Headquarter> findByCompanyNit(long nit);
    
}
