package com.proyect.Human_Resources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Company;

public interface ICompanyRepository extends JpaRepository<Company, Long> {
    // Custom query methods can be defined here if needed
}
