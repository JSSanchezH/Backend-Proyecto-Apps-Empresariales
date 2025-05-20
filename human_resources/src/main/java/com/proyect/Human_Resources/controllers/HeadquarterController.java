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

import com.proyect.Human_Resources.models.Headquarter;
import com.proyect.Human_Resources.models.UserCompany;
import com.proyect.Human_Resources.services.AuthService;
import com.proyect.Human_Resources.services.HeadquarterService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/headquarters")
public class HeadquarterController {

    @Autowired
    private HeadquarterService headquarterService; // Service to handle business logic for headquarters

    @Autowired
    private AuthService authService; // Service to handle authentication

    @GetMapping
    public ArrayList<Headquarter> getAllHeadquarters(HttpServletRequest request) {
        UserCompany userCompany = authService.getAuthenticatedUser(request); // Get the authenticated user
        return headquarterService.getHeadquarters(userCompany.getCompany().getNit()); // Returns all headquarters for the user's company
    }

    @PostMapping
    public Headquarter saveHeadquarter(@RequestBody Headquarter headquarter) {
        return headquarterService.saveHeadquarter(headquarter); // Saves a new headquarter and returns it
    }

    @PostMapping("/batch")
    public ArrayList<Headquarter> saveHeadquarters(@RequestBody ArrayList<Headquarter> headquarters) {
        return headquarterService.saveHeadquarters(headquarters); // Saves a list of headquarters and returns it
    }

    @GetMapping("/{id}")
    public Optional<Headquarter> getHeadquarterById(@PathVariable("id") long id) {
        return headquarterService.getHeadquarterById(id); // Returns a headquarter by its ID
    }

    @PutMapping("/{id}")
    public Headquarter updateHeadquarter(@RequestBody Headquarter headquarter, @PathVariable("id") long id) {
        return headquarterService.updateHeadquarter(headquarter, id); // Updates a headquarter by its ID and returns the updated headquarter
    }

    @DeleteMapping("/{id}")
    public String deleteHeadquarter(@PathVariable("id") long id) {
        boolean ok = headquarterService.deleteHeadquarter(id); // Deletes a headquarter by its ID
        if (ok) {
            return "Headquarter deleted successfully"; // Returns success message
        } else {
            return "Error deleting headquarter"; // Returns error message
        }
    }
    
}
