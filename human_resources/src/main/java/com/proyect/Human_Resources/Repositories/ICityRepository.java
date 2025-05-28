package com.proyect.Human_Resources.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.City;

public interface ICityRepository extends JpaRepository<City, Long> {
    // Custom query methods can be defined here if needed
    
    public ArrayList<City> findByStateId(Long id);
}
