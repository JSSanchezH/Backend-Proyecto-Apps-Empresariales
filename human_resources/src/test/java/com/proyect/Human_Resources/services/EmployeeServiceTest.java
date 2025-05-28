package com.proyect.Human_Resources.services;
import com.proyect.Human_Resources.models.Employee;
import com.proyect.Human_Resources.Repositories.IEmployeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    
    @Mock
    private IEmployeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setFirstname("John");
        employee.setLastname("Doe");
        employee.setEmail("john.doe@example.com");
        employee.setPhoneNumber("123456789");
        employee.setStatus(true);

        employeeList = new ArrayList<>();
        employeeList.add(employee);
    }

    @Test
    void testGetEmployeesByCompanyNit() {
        long nit = 123456789L;
        when(employeeRepository.findByDepartmentHeadquarterCompanyNit(nit)).thenReturn((ArrayList<Employee>) employeeList);

        ArrayList<Employee> result = employeeService.getEmployeesByCompanyNit(nit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstname());
        verify(employeeRepository).findByDepartmentHeadquarterCompanyNit(nit);
    }

    @Test
    void testSaveEmployee() {
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee saved = employeeService.saveEmployee(employee);

        assertNotNull(saved);
        assertEquals(employee.getFirstname(), saved.getFirstname());
        verify(employeeRepository).save(employee);
    }

    @Test
    void testSaveEmployees() {
        when(employeeRepository.saveAll(anyList())).thenReturn(employeeList);

        ArrayList<Employee> savedList = employeeService.saveEmployees(new ArrayList<>(employeeList));

        assertNotNull(savedList);
        assertEquals(1, savedList.size());
        verify(employeeRepository).saveAll(anyList());
    }

    @Test
    void testGetEmployeeById_Found() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> found = employeeService.getEmployeeById(1L);

        assertTrue(found.isPresent());
        assertEquals("John", found.get().getFirstname());
        verify(employeeRepository).findById(1L);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Employee> found = employeeService.getEmployeeById(1L);

        assertFalse(found.isPresent());
        verify(employeeRepository).findById(1L);
    }

    @Test
    void testUpdateEmployee() {
        Employee updatedData = new Employee();
        updatedData.setFirstname("Jane");
        updatedData.setLastname("Smith");
        updatedData.setEmail("jane.smith@example.com");
        updatedData.setPhoneNumber("987654321");
        updatedData.setStatus(false);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(i -> i.getArgument(0));

        Employee updated = employeeService.updateEmployee(updatedData, 1L);

        assertEquals("Jane", updated.getFirstname());
        assertEquals("Smith", updated.getLastname());
        assertEquals("jane.smith@example.com", updated.getEmail());
        assertEquals("987654321", updated.getPhoneNumber());
        assertFalse(updated.isStatus());
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee_Success() {
        doNothing().when(employeeRepository).deleteById(1L);

        boolean result = employeeService.deleteEmployee(1L);

        assertTrue(result);
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void testDeleteEmployee_Failure() {
        doThrow(new RuntimeException("Error deleting")).when(employeeRepository).deleteById(1L);

        boolean result = employeeService.deleteEmployee(1L);

        assertFalse(result);
        verify(employeeRepository).deleteById(1L);
    }
}
