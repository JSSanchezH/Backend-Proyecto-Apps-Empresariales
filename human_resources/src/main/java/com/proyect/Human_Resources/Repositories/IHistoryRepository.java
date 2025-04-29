package com.proyect.Human_Resources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.History;

public interface IHistoryRepository extends JpaRepository<History, Long> {
    // Custom query methods can be defined here if needed
    
}
