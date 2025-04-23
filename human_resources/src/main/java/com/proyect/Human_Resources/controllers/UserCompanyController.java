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
import com.proyect.Human_Resources.services.UserCompanyService;

@RestController
@RequestMapping("/UserCompany")
public class UserCompanyController {

    @Autowired
    private UserCompanyService userCompanyService; // Service to handle business logic for UserCompany

    // Define endpoints for CRUD operations on UserCompany here

    @GetMapping
    public ArrayList<UserCompany> getAllUserCompanies() {
        return userCompanyService.getUserCompanies(); // Returns a list of all UserCompany records
    }

    @PostMapping
    public UserCompany saveUserCompany(@RequestBody UserCompany userCompany) {
        return userCompanyService.saveUserCompany(userCompany); // Saves a new UserCompany record and returns it
    }

    @GetMapping("/{id}")
    public Optional<UserCompany> getUserCompanyById(@PathVariable("id") long id) {
        return userCompanyService.getUserCompanyById(id); // Returns a UserCompany record by its ID
    }

    @PutMapping("/{id}")
    public UserCompany updateUserCompany(@RequestBody UserCompany userCompany, @PathVariable("id") long id) {
        return userCompanyService.updateUserCompany(userCompany, id); // Updates a UserCompany record by its ID and returns the updated record
    }

    @DeleteMapping("/{id}")
    public String deleteUserCompany(@PathVariable("id") long id) {
        boolean ok = userCompanyService.deleteUserCompany(id); // Deletes a UserCompany record by its ID
        if (ok) {
            return "UserCompany deleted successfully"; // Returns success message
        } else {
            return "Error deleting UserCompany"; // Returns error message
        }
    }
}
