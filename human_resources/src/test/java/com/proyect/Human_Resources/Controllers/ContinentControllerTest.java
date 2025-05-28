package com.proyect.Human_Resources.Controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.ContinentController;
import com.proyect.Human_Resources.models.Continent;
import com.proyect.Human_Resources.services.ContinentService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan.Filter;
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
    controllers = ContinentController.class,
    excludeFilters = {
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class ContinentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContinentService continentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllContinents() throws Exception {
        Continent continent1 = new Continent(1L, "South America");
        Continent continent2 = new Continent(2L, "North America");

        Mockito.when(continentService.getContinents()).thenReturn(new ArrayList<>(Arrays.asList(continent1, continent2)));

        mockMvc.perform(get("/continents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("South America")))
                .andExpect(jsonPath("$[1].name", is("North America")));
    }

    @Test
    void testGetContinentById() throws Exception {
        Continent continent = new Continent(1L, "Europe");
        Mockito.when(continentService.getContinentById(1L)).thenReturn(Optional.of(continent));

        mockMvc.perform(get("/continents/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Europe")));
    }

    @Test
    void testSaveContinent() throws Exception {
        Continent input = new Continent(null, "Asia");
        Continent saved = new Continent(3L, "Asia");

        Mockito.when(continentService.saveContinent(Mockito.any(Continent.class))).thenReturn(saved);

        mockMvc.perform(post("/continents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Asia")));
    }

    @Test
    void testUpdateContinent() throws Exception {
        Continent updated = new Continent(1L, "Africa");

        Mockito.when(continentService.updateContinent(Mockito.any(Continent.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/continents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Africa")));
    }

    @Test
    void testDeleteContinentSuccess() throws Exception {
        Mockito.when(continentService.deleteContinent(1L)).thenReturn(true);

        mockMvc.perform(delete("/continents/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Continent deleted successfully"));
    }

    @Test
    void testDeleteContinentFail() throws Exception {
        Mockito.when(continentService.deleteContinent(1L)).thenReturn(false);

        mockMvc.perform(delete("/continents/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting continent"));
    }

    @Test
    void testSaveContinentsBatch() throws Exception {
        Continent input1 = new Continent(null, "Antarctica");
        Continent input2 = new Continent(null, "Oceania");

        Continent saved1 = new Continent(1L, "Antarctica");
        Continent saved2 = new Continent(2L, "Oceania");

        Mockito.when(continentService.saveContinents(Mockito.any()))
                .thenReturn(Arrays.asList(saved1, saved2));

        mockMvc.perform(post("/continents/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Arrays.asList(input1, input2))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Antarctica")))
                .andExpect(jsonPath("$[1].name", is("Oceania")));
    }
    
}
