package com.proyect.Human_Resources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Continent;

public interface IContinentRepository extends JpaRepository<Continent, Long> {
    // Custom query methods can be defined here if needed
}
