package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Payment_MethodTest {

     @Test
    public void testSettersAndGetters() {
        Payment_Method paymentMethod = new Payment_Method();

        Long id = 1L;
        String name = "Credit Card";

        paymentMethod.setId(id);
        paymentMethod.setName(name);

        assertEquals(id, paymentMethod.getId());
        assertEquals(name, paymentMethod.getName());
    }
}
