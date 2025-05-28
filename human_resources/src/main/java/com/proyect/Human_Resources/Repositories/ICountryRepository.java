package com.proyect.Human_Resources.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Country;

public interface ICountryRepository extends JpaRepository<Country, Long> {
    // Custom query methods can be defined here if needed´

    public ArrayList<Country> findByContinentId(Long id);
}
