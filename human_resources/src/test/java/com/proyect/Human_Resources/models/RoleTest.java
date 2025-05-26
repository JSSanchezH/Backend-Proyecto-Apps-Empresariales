package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RoleTest {
    
     @Test
    public void testSettersAndGetters() {
        Role role = new Role();

        Long id = 1L;
        String name = "ADMIN";

        role.setId(id);
        role.setName(name);

        assertEquals(id, role.getId());
        assertEquals(name, role.getName());
    }
}
