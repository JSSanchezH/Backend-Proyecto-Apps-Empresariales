package com.proyect.Human_Resources.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Absence_TypeTest {
    
     @Test
    public void testGettersAndSetters() {
        // Crear objeto
        Absence_Type absenceType = new Absence_Type();

        // Valores de prueba
        Long expectedId = 1L;
        String expectedName = "Vacaciones";

        // Usar setters
        absenceType.setId(expectedId);
        absenceType.setName(expectedName);

        // Verificar getters
        assertEquals(expectedId, absenceType.getId());
        assertEquals(expectedName, absenceType.getName());
    }
}
