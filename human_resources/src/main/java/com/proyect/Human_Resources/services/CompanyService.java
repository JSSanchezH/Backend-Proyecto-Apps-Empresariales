package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.ICompanyRepository;
import com.proyect.Human_Resources.models.Company;

@Service
public class CompanyService {

    @Autowired
    private ICompanyRepository companyRepository;

    public ArrayList<Company> getCompanies() {
        return (ArrayList<Company>) companyRepository.findAll();
    }

    public ArrayList<Company> saveCompanies(ArrayList<Company> companies) {
        return (ArrayList<Company>) companyRepository.saveAll(companies);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> getCompanyById(long id) {
        return companyRepository.findById(id);
    }

    public Company updateCompany(Company company, long id) {
        Company companyToUpdate = companyRepository.findById(id).get();
        companyToUpdate.setName(company.getName());
        companyToUpdate.setNit(company.getNit());
        companyToUpdate.setAddress(company.getAddress());
        companyToUpdate.setEmail(company.getEmail());
        companyToUpdate.setTypeIndustry(company.getTypeIndustry());
        companyToUpdate.setUrlLogo(company.getUrlLogo());
        return companyRepository.save(companyToUpdate);
    }

    public boolean deleteCompany(long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
