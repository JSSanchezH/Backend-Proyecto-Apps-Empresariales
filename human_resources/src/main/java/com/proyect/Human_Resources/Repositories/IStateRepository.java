package com.proyect.Human_Resources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.State;

public interface IStateRepository extends JpaRepository<State, Long> {
    // Custom query methods can be defined here if needed

}
