package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class DepartmentTest {
    
    @Test
    public void testGettersAndSetters() {
        Department department = new Department();
        Headquarter headquarter = new Headquarter();

        headquarter.setId(1L);
        headquarter.setName("Main HQ");

        department.setId(100L);
        department.setName("Finance");
        department.setHeadquarter(headquarter);

        assertEquals(100L, department.getId());
        assertEquals("Finance", department.getName());
        assertNotNull(department.getHeadquarter());
        assertEquals("Main HQ", department.getHeadquarter().getName());
    }
}
