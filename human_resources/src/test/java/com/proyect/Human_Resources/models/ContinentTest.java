package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ContinentTest {
    
    @Test
    public void testGettersAndSetters() {
        Continent continent = new Continent();

        continent.setId(1L);
        continent.setName("Europe");

        assertEquals(1L, continent.getId());
        assertEquals("Europe", continent.getName());
    }
}
