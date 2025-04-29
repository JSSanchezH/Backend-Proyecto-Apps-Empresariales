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

import com.proyect.Human_Resources.models.Department;
import com.proyect.Human_Resources.services.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ArrayList<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @PostMapping
    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PostMapping("/batch")
    public ArrayList<Department> saveDepartments(@RequestBody ArrayList<Department> departments) {
        return departmentService.saveDepartments(departments);
    }

    @GetMapping(path = "/{id}")
    public Optional<Department> getDepartmentById(@PathVariable("id") long id) {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping(path = "/{id}")
    public Department updateDepartment(@RequestBody Department department, @PathVariable("id") long id) {
        return departmentService.updateDepartment(department, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteDepartment(@PathVariable("id") long id) {
        boolean ok = departmentService.deleteDepartment(id);
        if (ok) {
            return "Department deleted successfully";
        } else {
            return "Error deleting department";
        }
    }    
}
