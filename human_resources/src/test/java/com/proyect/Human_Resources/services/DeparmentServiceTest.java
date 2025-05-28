package com.proyect.Human_Resources.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyect.Human_Resources.Repositories.IDepartmentRepository;
import com.proyect.Human_Resources.models.Department;
import com.proyect.Human_Resources.models.Headquarter;

@ExtendWith(MockitoExtension.class)
public class DeparmentServiceTest {

    @Mock
    private IDepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private Department department;
    private Headquarter headquarter;
    private ArrayList<Department> departments;

    @BeforeEach
    void setUp() {
        headquarter = new Headquarter();
        headquarter.setId(1L);
        headquarter.setName("Main Office");

        department = new Department();
        department.setId(1L);
        department.setName("IT Department");
        department.setHeadquarter(headquarter);

        departments = new ArrayList<>();
        departments.add(department);
    }

    @Test
    void testGetDepartmentsByCompanyNit() {
        long nit = 123456789L;
        when(departmentRepository.findByHeadquarterCompanyNit(nit)).thenReturn(departments);

        ArrayList<Department> result = departmentService.getDepartmentsByCompanyNit(nit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("IT Department", result.get(0).getName());
        verify(departmentRepository).findByHeadquarterCompanyNit(nit);
    }

    @Test
    void testSaveDepartment() {
        when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentService.saveDepartment(department);

        assertNotNull(result);
        assertEquals("IT Department", result.getName());
        verify(departmentRepository).save(department);
    }

    @Test
    void testSaveDepartments() {
        when(departmentRepository.saveAll(departments)).thenReturn(departments);

        ArrayList<Department> result = departmentService.saveDepartments(departments);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(departmentRepository).saveAll(departments);
    }

    @Test
    void testGetDepartmentById() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Optional<Department> result = departmentService.getDepartmentById(1L);

        assertTrue(result.isPresent());
        assertEquals("IT Department", result.get().getName());
        verify(departmentRepository).findById(1L);
    }

    @Test
    void testUpdateDepartment() {
        Department updatedDepartment = new Department();
        updatedDepartment.setName("HR Department");
        updatedDepartment.setHeadquarter(headquarter);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Department result = departmentService.updateDepartment(updatedDepartment, 1L);

        assertNotNull(result);
        verify(departmentRepository).findById(1L);
        verify(departmentRepository).save(department);
    }

    @Test
    void testDeleteDepartmentSuccess() {
        doNothing().when(departmentRepository).deleteById(1L);

        boolean result = departmentService.deleteDepartment(1L);

        assertTrue(result);
        verify(departmentRepository).deleteById(1L);
    }

    @Test
    void testDeleteDepartmentFailure() {
        doThrow(new RuntimeException()).when(departmentRepository).deleteById(1L);

        boolean result = departmentService.deleteDepartment(1L);

        assertFalse(result);
        verify(departmentRepository).deleteById(1L);
    }
    
}
