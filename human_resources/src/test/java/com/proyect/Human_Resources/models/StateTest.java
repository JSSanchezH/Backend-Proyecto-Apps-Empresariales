package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class StateTest {

    @Test
    public void testGettersAndSetters() {
        State state = new State();

        Country country = new Country();
        country.setId(1L);
        country.setName("TestCountry");

        state.setId(10L);
        state.setName("TestState");
        state.setCountry(country);

        assertEquals(10L, state.getId());
        assertEquals("TestState", state.getName());
        assertNotNull(state.getCountry());
        assertEquals("TestCountry", state.getCountry().getName());
    }
    
}
