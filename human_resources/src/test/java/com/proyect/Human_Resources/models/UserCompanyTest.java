package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class UserCompanyTest {
    
    @Test
    public void testGettersAndSetters() {
        UserCompany userCompany = new UserCompany();

        Company company = new Company();
        company.setId(1L);
        company.setName("TestCompany");

        userCompany.setId(100L);
        userCompany.setCompany(company);
        userCompany.setUserName("testuser");
        userCompany.setPassword("securePassword123");
        userCompany.setApiKey("APIKEY_123456");

        assertEquals(100L, userCompany.getId());
        assertNotNull(userCompany.getCompany());
        assertEquals("TestCompany", userCompany.getCompany().getName());
        assertEquals("testuser", userCompany.getUserName());
        assertEquals("securePassword123", userCompany.getPassword());
        assertEquals("APIKEY_123456", userCompany.getApiKey());
    }
}
