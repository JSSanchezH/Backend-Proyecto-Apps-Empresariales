package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IWorkAbsencesRepository;
import com.proyect.Human_Resources.models.WorkAbsences;

@Service
public class WorkAbsencesService {
    
    @Autowired
    private IWorkAbsencesRepository workAbsencesRepository; // Injecting the IWorkAbsencesRepository dependency

    public ArrayList<WorkAbsences> getWorkAbsences() {
        return (ArrayList<WorkAbsences>) workAbsencesRepository.findAll(); // Retrieves all work absences records from the database
    }

    public WorkAbsences saveWorkAbsences(WorkAbsences workAbsences) {
        return workAbsencesRepository.save(workAbsences); // Saves the work absences record to the database and returns the saved record
    }

    public Optional<WorkAbsences> getWorkAbsencesById(long id) {
        return workAbsencesRepository.findById(id); // Retrieves the work absences record by its ID
    }

    public WorkAbsences updateWorkAbsences(WorkAbsences workAbsences, long id) {
        WorkAbsences workAbsencesToUpdate = workAbsencesRepository.findById(id).get(); // Retrieves the work absences record to update
        workAbsencesToUpdate.setEmployee(workAbsences.getEmployee()); // Updates the employee associated with the work absence
        workAbsencesToUpdate.setAbsenceType(workAbsences.getAbsenceType()); // Updates the type of absence associated with the work absence
        workAbsencesToUpdate.setStartDate(workAbsences.getStartDate()); // Updates the start date of the work absence
        workAbsencesToUpdate.setEndDate(workAbsences.getEndDate()); // Updates the end date of the work absence
        workAbsencesToUpdate.setDescription(workAbsences.getDescription()); // Updates the reason for the work absence
        return workAbsencesRepository.save(workAbsencesToUpdate); // Saves the updated work absences record and returns it
    }

    public boolean deleteWorkAbsences(long id) {
        try {
            workAbsencesRepository.deleteById(id); // Deletes the work absences record by its ID
            return true; // Returns true if deletion was successful
        } catch (Exception e) {
            return false; // Returns false if there was an error during deletion
        }
    }
}
