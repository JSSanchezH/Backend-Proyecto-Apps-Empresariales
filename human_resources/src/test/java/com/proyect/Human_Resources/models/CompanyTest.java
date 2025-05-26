package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CompanyTest {
    
    @Test
    public void testGettersAndSetters() {
        Company company = new Company();

        company.setId(1L);
        company.setName("TechCorp");
        company.setNit(123456789);
        company.setAddress("123 Main Street");
        company.setEmail("info@techcorp.com");
        company.setTypeIndustry("Software");
        company.setUrlLogo("http://techcorp.com/logo.png");

        assertEquals(1L, company.getId());
        assertEquals("TechCorp", company.getName());
        assertEquals(123456789, company.getNit());
        assertEquals("123 Main Street", company.getAddress());
        assertEquals("info@techcorp.com", company.getEmail());
        assertEquals("Software", company.getTypeIndustry());
        assertEquals("http://techcorp.com/logo.png", company.getUrlLogo());
    }
}
