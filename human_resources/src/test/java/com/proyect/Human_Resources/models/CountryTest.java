package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CountryTest {
    
    @Test
    public void testGettersAndSetters() {
        Country country = new Country();
        Continent continent = new Continent();

        continent.setId(1L);
        continent.setName("Asia");

        country.setId(10L);
        country.setName("Japan");
        country.setContinent(continent);

        assertEquals(10L, country.getId());
        assertEquals("Japan", country.getName());
        assertNotNull(country.getContinent());
        assertEquals("Asia", country.getContinent().getName());
    }
}
