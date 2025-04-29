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

import com.proyect.Human_Resources.models.Company;
import com.proyect.Human_Resources.services.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ArrayList<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @PostMapping
    public Company saveCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @PostMapping("/batch")
    public ArrayList<Company> saveCompanies(@RequestBody ArrayList<Company> companies) {
        return companyService.saveCompanies(companies);
    }

    @GetMapping("/{id}")
    public Optional<Company> getCompanyById(@PathVariable("id") long id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@RequestBody Company company, @PathVariable("id") long id) {
        return companyService.updateCompany(company, id);
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable("id") long id) {
        boolean ok = companyService.deleteCompany(id);
        if (ok) {
            return "Company deleted successfully";
        } else {
            return "Error deleting company";
        }
    }    
}
