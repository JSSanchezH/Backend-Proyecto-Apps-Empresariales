package com.proyect.Human_Resources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Role;

public interface IRoleReposiotry extends JpaRepository<Role, Long> {
    // Custom query methods can be defined here if needed

    
}
