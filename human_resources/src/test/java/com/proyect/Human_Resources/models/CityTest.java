package com.proyect.Human_Resources.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CityTest {

    @Test
    public void testGettersAndSetters() {
        City city = new City();
        State state = new State();
        state.setId(1L);
        state.setName("Antioquia");

        city.setId(10L);
        city.setName("Medellín");
        city.setState(state);

        assertEquals(10L, city.getId());
        assertEquals("Medellín", city.getName());
        assertEquals(state, city.getState());
        assertEquals("Antioquia", city.getState().getName());
    }
}
