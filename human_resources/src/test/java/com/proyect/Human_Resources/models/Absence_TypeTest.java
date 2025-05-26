package com.proyect.Human_Resources.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Absence_TypeTest {
    
     @Test
    public void testGettersAndSetters() {

        Absence_Type absenceType = new Absence_Type();

       
        Long expectedId = 1L;
        String expectedName = "Vacaciones";

        
        absenceType.setId(expectedId);
        absenceType.setName(expectedName);

        
        assertEquals(expectedId, absenceType.getId());
        assertEquals(expectedName, absenceType.getName());
    }
}
