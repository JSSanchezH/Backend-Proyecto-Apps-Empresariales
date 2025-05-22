package com.proyect.Human_Resources.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.UserCompany;


public interface IUserCompanyRepository extends JpaRepository<UserCompany, Long> {
    // Custom query methods can be defined here if needed

    public UserCompany findByApiKey(String apiKey); // Method to find a UserCompany by API key

    public Optional <UserCompany> findByUserName(String userName); // Method to find a UserCompany by user Name

}
