package com.proyect.Human_Resources.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.Human_Resources.models.UserCompany;
import com.proyect.Human_Resources.models.WorkAbsences;
import com.proyect.Human_Resources.services.AuthService;
import com.proyect.Human_Resources.services.WorkAbsencesService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/absences")
public class WorkAbsenceController {

    @Autowired
    private WorkAbsencesService workAbsencesService; // Injecting the WorkAbsencesService dependency

    @Autowired
    private AuthService authService; // Injecting the AuthService dependency

    // Define endpoints for handling HTTP requests related to work absences here

    @GetMapping
    public ArrayList<WorkAbsences> getAllWorkAbsences(HttpServletRequest request) {
        UserCompany userCompany = authService.getAuthenticatedUser(request); // Get the authenticated user
        return workAbsencesService.getWorkAbsences(userCompany.getCompany().getNit()); // Returns all work absences for the user's company
    }

    @PostMapping
    public WorkAbsences createWorkAbsence(@RequestBody WorkAbsences workAbsences) {
        return workAbsencesService.saveWorkAbsences(workAbsences); // Saves the new work absence record using the service
    }

    @PostMapping("/batch")
    public ArrayList<WorkAbsences> createWorkAbsences(@RequestBody ArrayList<WorkAbsences> workAbsences) {
        return workAbsencesService.saveWorkAbsences(workAbsences); // Saves a list of work absence records using the service
    }

    @GetMapping("/{id}")
    public Optional<WorkAbsences> getWorkAbsenceById(@PathVariable long id) {
        return workAbsencesService.getWorkAbsencesById(id); // Retrieves a work absence record by its ID using the service
    }

    @PutMapping("/{id}")
    public WorkAbsences updateWorkAbsence(@RequestBody WorkAbsences workAbsences, @PathVariable long id) {
        return workAbsencesService.updateWorkAbsences(workAbsences, id); // Updates the work absence record using the service
    }

    @DeleteMapping("/{id}")
    public String deleteWorkAbsence(@PathVariable long id) {
        if (workAbsencesService.deleteWorkAbsences(id)) {
            return "Work absence deleted successfully"; // Returns a success message if deletion was successful
        } else {
            return "Error deleting work absence"; // Returns an error message if deletion failed
        }
    }

    
}
