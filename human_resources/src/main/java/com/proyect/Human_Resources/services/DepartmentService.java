package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IDepartmentRepository;
import com.proyect.Human_Resources.models.Department;

@Service
public class DepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository; // Repository for accessing department data

    // Method to save a department

    public ArrayList<Department> getDepartmentsByCompanyNit(long nit) {
        return departmentRepository.findByHeadquarterCompanyNit(nit); // Retrieve all departments from the repository
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department); // Save the department to the repository
    }

    public ArrayList<Department> saveDepartments(ArrayList<Department> departments) {
        return (ArrayList<Department>) departmentRepository.saveAll(departments); // Save a list of departments to the repository
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id); // Retrieve a department by its ID from the repository
    }

    public Department updateDepartment(Department department, Long id) {
        Department departmentToUpdate = departmentRepository.findById(id).get(); // Retrieve the department to update
        departmentToUpdate.setName(department.getName()); // Update the name of the department
        departmentToUpdate.setHeadquarter(department.getHeadquarter()); // Update the headquarter of the department
        return departmentRepository.save(departmentToUpdate); // Save the updated department to the repository
    }

    public boolean deleteDepartment(Long id) {
        try {
            departmentRepository.deleteById(id); // Delete the department by its ID from the repository
            return true; // Return true if deletion was successful
        } catch (Exception e) {
            return false; // Return false if there was an error during deletion
        }
    }
    
}
