package com.proyect.Human_Resources.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

public class EmployeeTest {

    @Test
    public void testEmployeeSettersAndGetters() {
        Employee employee = new Employee();

        Long id = 1L;
        String firstname = "Juan";
        String lastname = "Pérez";
        String email = "juan.perez@example.com";
        String phoneNumber = "123456789";
        Date hireDate = new Date(System.currentTimeMillis()); 
        Role role = new Role();
        Department department = new Department();
        String urlFoto = "http://example.com/foto.jpg";
        boolean status = true;

        employee.setId(id);
        employee.setFirstname(firstname);
        employee.setLastname(lastname);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setHireDate(hireDate);
        employee.setRole(role);
        employee.setDepartment(department);
        employee.setUrlFoto(urlFoto);
        employee.setStatus(status);

        assertEquals(id, employee.getId());
        assertEquals(firstname, employee.getFirstname());
        assertEquals(lastname, employee.getLastname());
        assertEquals(email, employee.getEmail());
        assertEquals(phoneNumber, employee.getPhoneNumber());
        assertEquals(hireDate, employee.getHireDate());
        assertEquals(role, employee.getRole());
        assertEquals(department, employee.getDepartment());
        assertEquals(urlFoto, employee.getUrlFoto());
        assertTrue(employee.isStatus());
    }
    
}
