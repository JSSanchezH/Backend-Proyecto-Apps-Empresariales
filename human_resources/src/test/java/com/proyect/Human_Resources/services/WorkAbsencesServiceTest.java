package com.proyect.Human_Resources.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyect.Human_Resources.Repositories.IWorkAbsencesRepository;
import com.proyect.Human_Resources.models.WorkAbsences;
import com.proyect.Human_Resources.models.Employee;
import com.proyect.Human_Resources.models.Absence_Type;

@ExtendWith(MockitoExtension.class)
public class WorkAbsencesServiceTest {

     @Mock
    private IWorkAbsencesRepository workAbsencesRepository;

    @InjectMocks
    private WorkAbsencesService workAbsencesService;

    private WorkAbsences workAbsences;
    private Employee employee;
    private Absence_Type absenceType;
    private ArrayList<WorkAbsences> workAbsencesList;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setFirstname("Test Employee");

        absenceType = new Absence_Type();
        absenceType.setId(1L);
        absenceType.setName("Sick Leave");

        workAbsences = new WorkAbsences();
        workAbsences.setId(1L);
        workAbsences.setEmployee(employee);
        workAbsences.setAbsenceType(absenceType);
        workAbsences.setStartDate(Date.valueOf("2024-01-15"));
        workAbsences.setEndDate(Date.valueOf("2024-01-17"));
        workAbsences.setDescription("Medical appointment");

        workAbsencesList = new ArrayList<>();
        workAbsencesList.add(workAbsences);
    }

    @Test
    void testGetWorkAbsences() {
        long nit = 123456789L;
        when(workAbsencesRepository.findByEmployeeDepartmentHeadquarterCompanyNit(nit)).thenReturn(workAbsencesList);

        ArrayList<WorkAbsences> result = workAbsencesService.getWorkAbsences(nit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Medical appointment", result.get(0).getDescription());
        verify(workAbsencesRepository).findByEmployeeDepartmentHeadquarterCompanyNit(nit);
    }

    @Test
    void testSaveWorkAbsences() {
        when(workAbsencesRepository.save(workAbsences)).thenReturn(workAbsences);

        WorkAbsences result = workAbsencesService.saveWorkAbsences(workAbsences);

        assertNotNull(result);
        assertEquals("Medical appointment", result.getDescription());
        assertEquals(Date.valueOf("2024-01-15"), result.getStartDate());
        verify(workAbsencesRepository).save(workAbsences);
    }

    @Test
    void testSaveWorkAbsencesList() {
        when(workAbsencesRepository.saveAll(workAbsencesList)).thenReturn(workAbsencesList);

        ArrayList<WorkAbsences> result = workAbsencesService.saveWorkAbsences(workAbsencesList);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(workAbsencesRepository).saveAll(workAbsencesList);
    }

    @Test
    void testGetWorkAbsencesById() {
        when(workAbsencesRepository.findById(1L)).thenReturn(Optional.of(workAbsences));

        Optional<WorkAbsences> result = workAbsencesService.getWorkAbsencesById(1L);

        assertTrue(result.isPresent());
        assertEquals("Medical appointment", result.get().getDescription());
        verify(workAbsencesRepository).findById(1L);
    }

    @Test
    void testUpdateWorkAbsences() {
        WorkAbsences updatedWorkAbsences = new WorkAbsences();
        updatedWorkAbsences.setEmployee(employee);
        updatedWorkAbsences.setAbsenceType(absenceType);
        updatedWorkAbsences.setStartDate(Date.valueOf("2024-02-01"));
        updatedWorkAbsences.setEndDate(Date.valueOf("2024-02-03"));
        updatedWorkAbsences.setDescription("Updated medical leave");

        when(workAbsencesRepository.findById(1L)).thenReturn(Optional.of(workAbsences));
        when(workAbsencesRepository.save(any(WorkAbsences.class))).thenReturn(workAbsences);

        WorkAbsences result = workAbsencesService.updateWorkAbsences(updatedWorkAbsences, 1L);

        assertNotNull(result);
        verify(workAbsencesRepository).findById(1L);
        verify(workAbsencesRepository).save(workAbsences);
    }

    @Test
    void testDeleteWorkAbsencesSuccess() {
        doNothing().when(workAbsencesRepository).deleteById(1L);

        boolean result = workAbsencesService.deleteWorkAbsences(1L);

        assertTrue(result);
        verify(workAbsencesRepository).deleteById(1L);
    }

    @Test
    void testDeleteWorkAbsencesFailure() {
        doThrow(new RuntimeException()).when(workAbsencesRepository).deleteById(1L);

        boolean result = workAbsencesService.deleteWorkAbsences(1L);

        assertFalse(result);
        verify(workAbsencesRepository).deleteById(1L);
    }
    
}
