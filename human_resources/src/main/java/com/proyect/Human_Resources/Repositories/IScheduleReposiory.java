package com.proyect.Human_Resources.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Schedule;

public interface IScheduleReposiory extends JpaRepository<Schedule, Long> {
    // Custom query methods can be defined here if needed

    public ArrayList<Schedule> findByEmployeeDepartmentHeadquarterCompanyNit(long nit); // Find schedules by company NIT
}

