package com.proyect.Human_Resources.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.HeadquarterController;
import com.proyect.Human_Resources.models.*;
import com.proyect.Human_Resources.services.AuthService;
import com.proyect.Human_Resources.services.HeadquarterService;

import jakarta.servlet.http.HttpServletRequest;
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
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = HeadquarterController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class HeadquarterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeadquarterService headquarterService;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    // Estructura jerárquica para el test
    private final Continent continent = new Continent(1L, "South America");
    private final Country country = new Country(1L, "Colombia", continent);
    private final State state = new State(1L, "Quindío", country);
    private final City city = new City(1L, "Armenia", state);
    private final Company company = new Company(1L, "Tech Corp", 123456789L, "123 Main St", "info@techcorp.com", "Technology", "http://logo.url");
    private final UserCompany userCompany = new UserCompany(1L, company, "user", "pass", "token");

    @Test
    void testGetAllHeadquarters() throws Exception {
        ArrayList<Headquarter> headquarters = new ArrayList<>();
        headquarters.add(new Headquarter(1L, "Main Office", "456 Business Ave", 5551234567L, city, company));
        headquarters.add(new Headquarter(2L, "Branch Office", "789 Corporate St", 5559876543L, city, company));

        Mockito.when(authService.getAuthenticatedUser(Mockito.any(HttpServletRequest.class))).thenReturn(userCompany);
        Mockito.when(headquarterService.getHeadquarters(123456789L)).thenReturn(headquarters);

        mockMvc.perform(get("/headquarters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Main Office")))
                .andExpect(jsonPath("$[0].address", is("456 Business Ave")))
                .andExpect(jsonPath("$[1].name", is("Branch Office")))
                .andExpect(jsonPath("$[1].address", is("789 Corporate St")));
    }

    @Test
    void testGetHeadquarterById() throws Exception {
        Headquarter headquarter = new Headquarter(1L, "Main Office", "456 Business Ave", 5551234567L, city, company);

        Mockito.when(headquarterService.getHeadquarterById(1L)).thenReturn(Optional.of(headquarter));

        mockMvc.perform(get("/headquarters/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Main Office")))
                .andExpect(jsonPath("$.address", is("456 Business Ave")));
    }

    @Test
    void testGetHeadquarterByIdNotFound() throws Exception {
        Mockito.when(headquarterService.getHeadquarterById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/headquarters/999"))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveHeadquarter() throws Exception {
        Headquarter input = new Headquarter(4L,"New Office", "123 New St", 5551111111L, city, company);
        Headquarter saved = new Headquarter(3L, "New Office", "123 New St", 5551111111L, city, company);

        Mockito.when(headquarterService.saveHeadquarter(Mockito.any(Headquarter.class))).thenReturn(saved);

        mockMvc.perform(post("/headquarters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("New Office")))
                .andExpect(jsonPath("$.address", is("123 New St")));
    }

    @Test
    void testUpdateHeadquarter() throws Exception {
        Headquarter updated = new Headquarter(1L, "Updated Office", "456 Updated Ave", 5552222222L, city, company);

        Mockito.when(headquarterService.updateHeadquarter(Mockito.any(Headquarter.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/headquarters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Office")))
                .andExpect(jsonPath("$.address", is("456 Updated Ave")));
    }

    @Test
    void testDeleteHeadquarterSuccess() throws Exception {
        Mockito.when(headquarterService.deleteHeadquarter(1L)).thenReturn(true);

        mockMvc.perform(delete("/headquarters/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Headquarter deleted successfully"));
    }

    @Test
    void testDeleteHeadquarterFail() throws Exception {
        Mockito.when(headquarterService.deleteHeadquarter(1L)).thenReturn(false);

        mockMvc.perform(delete("/headquarters/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting headquarter"));
    }

    @Test
    void testSaveHeadquartersBatch() throws Exception {
        ArrayList<Headquarter> inputHeadquarters = new ArrayList<>();
        inputHeadquarters.add(new Headquarter(1L, "Office A", "111 Street A", 5551111111L, city, company));
        inputHeadquarters.add(new Headquarter(2L, "Office B", "222 Street B", 5552222222L, city, company));

        ArrayList<Headquarter> savedHeadquarters = new ArrayList<>();
        savedHeadquarters.add(new Headquarter(4L, "Office A", "111 Street A", 5551111111L, city, company));
        savedHeadquarters.add(new Headquarter(5L, "Office B", "222 Street B", 5552222222L, city, company));

        Mockito.when(headquarterService.saveHeadquarters(Mockito.any(ArrayList.class))).thenReturn(savedHeadquarters);

        mockMvc.perform(post("/headquarters/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputHeadquarters)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(4)))
                .andExpect(jsonPath("$[0].name", is("Office A")))
                .andExpect(jsonPath("$[0].address", is("111 Street A")))
                .andExpect(jsonPath("$[1].id", is(5)))
                .andExpect(jsonPath("$[1].name", is("Office B")))
                .andExpect(jsonPath("$[1].address", is("222 Street B")));
    }
    
}
