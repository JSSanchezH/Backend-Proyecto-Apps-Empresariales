package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IEmployeRepository;
import com.proyect.Human_Resources.models.Employee;

@Service
public class EmployeeService {

    @Autowired
    private IEmployeRepository employeeRepository; // Repository for accessing employee data

    public ArrayList<Employee> getEmployeesByCompanyNit(long nit) {
        return employeeRepository.findByDepartmentHeadquarterCompanyNit(nit);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee); // Saves the provided employee to the database
    }

    public ArrayList<Employee> saveEmployees(ArrayList<Employee> employees) {
        return (ArrayList<Employee>) employeeRepository.saveAll(employees); // Saves a list of employees to the database
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id); // Retrieves an employee by their ID from the database
    }

    public Employee updateEmployee(Employee employee, Long id) {
        Employee employeeToUpdate = employeeRepository.findById(id).get();
        employeeToUpdate.setFirstname(employee.getFirstname()); // Updates the first name of the employee
        employeeToUpdate.setLastname(employee.getLastname()); // Updates the last name of the employee
        employeeToUpdate.setEmail(employee.getEmail()); // Updates the email of the employee
        employeeToUpdate.setPhoneNumber(employee.getPhoneNumber()); // Updates the phone number of the employee
        employeeToUpdate.setHireDate(employee.getHireDate()); // Updates the hire date of the employee
        employeeToUpdate.setRole(employee.getRole()); // Updates the role of the employee
        employeeToUpdate.setDepartment(employee.getDepartment()); // Updates the department of the employee
        employeeToUpdate.setUrlFoto(employee.getUrlFoto()); // Updates the photo URL of the employee
        employeeToUpdate.setStatus(employee.isStatus()); // Updates the status of the employee (active/inactive)
        return employeeRepository.save(employeeToUpdate); // Saves the updated employee to the database
    }

    public boolean deleteEmployee(Long id) {
        try {
            employeeRepository.deleteById(id); // Deletes the employee by their ID from the database
            return true; // Returns true if the deletion was successful
        } catch (Exception err) {
            return false; // Returns false if there was an error during deletion
        }
    }

}
