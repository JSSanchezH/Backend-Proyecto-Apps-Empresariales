package com.proyect.Human_Resources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Absence_Type;

public interface IAbsenceTypeRepository extends JpaRepository<Absence_Type, Long> {
    // Custom query methods can be defined here if needed
}
