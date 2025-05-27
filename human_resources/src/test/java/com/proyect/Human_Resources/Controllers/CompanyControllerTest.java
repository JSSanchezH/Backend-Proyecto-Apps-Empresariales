package com.proyect.Human_Resources.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.CompanyController;
import com.proyect.Human_Resources.models.Company;
import com.proyect.Human_Resources.services.CompanyService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(
    controllers = CompanyController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetCompanies() throws Exception {
        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("Tech Corp");

        Company company2 = new Company();
        company2.setId(2L);
        company2.setName("Business Solutions");

        ArrayList<Company> companies = new ArrayList<>(Arrays.asList(company1, company2));
        Mockito.when(companyService.getCompanies()).thenReturn(companies);

        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Tech Corp")))
                .andExpect(jsonPath("$[1].name", is("Business Solutions")));
    }

    @Test
    void testGetCompanyById() throws Exception {
        Company company = new Company();
        company.setId(1L);
        company.setName("Tech Corp");

        Mockito.when(companyService.getCompanyById(1L)).thenReturn(Optional.of(company));

        mockMvc.perform(get("/companies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Tech Corp")));
    }

    @Test
    void testSaveCompany() throws Exception {
        Company input = new Company();
        input.setName("New Company");

        Company saved = new Company();
        saved.setId(10L);
        saved.setName("New Company");

        Mockito.when(companyService.saveCompany(Mockito.any(Company.class))).thenReturn(saved);

        mockMvc.perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("New Company")));
    }

    @Test
    void testUpdateCompany() throws Exception {
        Company updated = new Company();
        updated.setId(1L);
        updated.setName("Updated Company");

        Mockito.when(companyService.updateCompany(Mockito.any(Company.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/companies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Company")));
    }

    @Test
    void testDeleteCompanySuccess() throws Exception {
        Mockito.when(companyService.deleteCompany(1L)).thenReturn(true);

        mockMvc.perform(delete("/companies/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Company deleted successfully"));
    }

    @Test
    void testDeleteCompanyFail() throws Exception {
        Mockito.when(companyService.deleteCompany(1L)).thenReturn(false);

        mockMvc.perform(delete("/companies/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting company"));
    }

    @Test
    void testSaveCompaniesBatch() throws Exception {
        Company company1 = new Company();
        company1.setName("Company A");

        Company company2 = new Company();
        company2.setName("Company B");

        ArrayList<Company> inputList = new ArrayList<>(Arrays.asList(company1, company2));

        Company saved1 = new Company();
        saved1.setId(1L);
        saved1.setName("Company A");

        Company saved2 = new Company();
        saved2.setId(2L);
        saved2.setName("Company B");

        ArrayList<Company> savedList = new ArrayList<>(Arrays.asList(saved1, saved2));

        Mockito.when(companyService.saveCompanies(Mockito.any())).thenReturn(savedList);

        mockMvc.perform(post("/companies/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }
    
}
