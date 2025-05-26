package com.proyect.Human_Resources.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyect.Human_Resources.Repositories.ICompanyRepository;
import com.proyect.Human_Resources.models.Company;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private ICompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    private Company company;
    private ArrayList<Company> companies;

    @BeforeEach
    void setUp() {
        company = new Company();
        company.setId(1L);
        company.setName("Test Company");
        company.setNit(123456789L);
        company.setEmail("test@company.com");
        company.setAddress("Test Address");
        company.setTypeIndustry("Technology");
        company.setUrlLogo("http://logo.url");

        companies = new ArrayList<>();
        companies.add(company);
    }

    @Test
    void testGetCompanies() {
        when(companyRepository.findAll()).thenReturn(companies);

        ArrayList<Company> result = companyService.getCompanies();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Company", result.get(0).getName());
        verify(companyRepository).findAll();
    }

    @Test
    void testSaveCompanies() {
        when(companyRepository.saveAll(companies)).thenReturn(companies);

        ArrayList<Company> result = companyService.saveCompanies(companies);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(companyRepository).saveAll(companies);
    }

    @Test
    void testSaveCompany() {
        when(companyRepository.save(company)).thenReturn(company);

        Company result = companyService.saveCompany(company);

        assertNotNull(result);
        assertEquals("Test Company", result.getName());
        verify(companyRepository).save(company);
    }

    @Test
    void testGetCompanyById() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        Optional<Company> result = companyService.getCompanyById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test Company", result.get().getName());
        verify(companyRepository).findById(1L);
    }

    @Test
    void testUpdateCompany() {
        Company updatedCompany = new Company();
        updatedCompany.setName("Updated Company");
        updatedCompany.setNit(987654321L);
        updatedCompany.setEmail("updated@company.com");

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company result = companyService.updateCompany(updatedCompany, 1L);

        assertNotNull(result);
        verify(companyRepository).findById(1L);
        verify(companyRepository).save(company);
    }

    @Test
    void testDeleteCompanySuccess() {
        doNothing().when(companyRepository).deleteById(1L);

        boolean result = companyService.deleteCompany(1L);

        assertTrue(result);
        verify(companyRepository).deleteById(1L);
    }

    @Test
    void testDeleteCompanyFailure() {
        doThrow(new RuntimeException()).when(companyRepository).deleteById(1L);

        boolean result = companyService.deleteCompany(1L);

        assertFalse(result);
        verify(companyRepository).deleteById(1L);
    }
    
}
