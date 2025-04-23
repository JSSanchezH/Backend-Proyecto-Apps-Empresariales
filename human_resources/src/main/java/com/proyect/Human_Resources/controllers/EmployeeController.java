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

import com.proyect.Human_Resources.models.Employee;
import com.proyect.Human_Resources.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService; // Service for handling employee-related operations

    // Endpoint to get all employees

    @GetMapping
    public ArrayList<Employee> getEmployees() {
        return employeeService.getEmployees(); // Retrieves all employees from the service
    }

    // Endpoint to save a new employee
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee); // Saves the provided employee to the service
    }

    // Endpoint to get an employee by ID
    @GetMapping(path = "/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") long id) {
        return employeeService.getEmployeeById(id); // Retrieves an employee by their ID from the service
    }

    // Endpoint to update an employee by ID
    @PutMapping(path = "/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") long id) {
        return employeeService.updateEmployee(employee, id); // Updates the provided employee in the service
    }

    // Endpoint to delete an employee by ID
    @DeleteMapping(path = "/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        boolean ok = employeeService.deleteEmployee(id); // Deletes the employee by their ID from the service
        if (ok) {
            return "Employee deleted successfully"; // Returns success message if deletion was successful
        } else {
            return "Error deleting employee"; // Returns error message if deletion failed
        }
    }
    
}
