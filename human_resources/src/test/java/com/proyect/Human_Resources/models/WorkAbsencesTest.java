package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.Test;

public class WorkAbsencesTest {
    
    @Test
    public void testGettersAndSetters() {
        WorkAbsences workAbsence = new WorkAbsences();

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstname("John Doe");

        Absence_Type absenceType = new Absence_Type();
        absenceType.setId(1L);
        absenceType.setName("Sick Leave");

        Date startDate = new Date(System.currentTimeMillis());
        Date endDate = new Date(startDate.getTime() + 86400000L); // +1 día

        
        workAbsence.setId(10L);
        workAbsence.setEmployee(employee);
        workAbsence.setAbsenceType(absenceType);
        workAbsence.setStartDate(startDate);
        workAbsence.setEndDate(endDate);
        workAbsence.setDescription("Fever and flu");

        
        assertEquals(10L, workAbsence.getId());
        assertNotNull(workAbsence.getEmployee());
        assertEquals("John Doe", workAbsence.getEmployee().getFirstname());
        assertNotNull(workAbsence.getAbsenceType());
        assertEquals("Sick Leave", workAbsence.getAbsenceType().getName());
        assertEquals(startDate, workAbsence.getStartDate());
        assertEquals(endDate, workAbsence.getEndDate());
        assertEquals("Fever and flu", workAbsence.getDescription());
    }
}
