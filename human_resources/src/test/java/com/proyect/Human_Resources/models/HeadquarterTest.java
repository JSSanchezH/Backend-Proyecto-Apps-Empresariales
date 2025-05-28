package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HeadquarterTest {

    @Test
    public void testHeadquarterSettersAndGetters() {
        Headquarter headquarter = new Headquarter();

        long id = 1L;
        String name = "Main Office";
        String address = "123 Main Street";
        long phone = 1234567890L;
        City city = new City();
        Company company = new Company();

        headquarter.setId(id);
        headquarter.setName(name);
        headquarter.setAddress(address);
        headquarter.setPhone(phone);
        headquarter.setCity(city);
        headquarter.setCompany(company);

        assertEquals(id, headquarter.getId());
        assertEquals(name, headquarter.getName());
        assertEquals(address, headquarter.getAddress());
        assertEquals(phone, headquarter.getPhone());
        assertEquals(city, headquarter.getCity());
        assertEquals(company, headquarter.getCompany());
    }
    
}
