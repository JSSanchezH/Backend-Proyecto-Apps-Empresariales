package com.proyect.Human_Resources.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.CountryController;
import com.proyect.Human_Resources.models.Continent;
import com.proyect.Human_Resources.models.Country;
import com.proyect.Human_Resources.services.CountryService;

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
    controllers = CountryController.class,
    excludeFilters = {
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class CountryControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Continent continent = new Continent(1L, "South America");

    @Test
    void testGetAllCountries() throws Exception {
        Country country1 = new Country(1L, "Colombia", continent);
        Country country2 = new Country(2L, "Argentina", continent);

        Mockito.when(countryService.getCountries()).thenReturn(new ArrayList<>(Arrays.asList(country1, country2)));

        mockMvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Colombia")))
                .andExpect(jsonPath("$[0].continent.name", is("South America")))
                .andExpect(jsonPath("$[1].name", is("Argentina")));
    }

    @Test
    void testGetCountryById() throws Exception {
        Country country = new Country(1L, "Brazil", continent);
        Mockito.when(countryService.getCountryById(1L)).thenReturn(Optional.of(country));

        mockMvc.perform(get("/countries/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Brazil")))
                .andExpect(jsonPath("$.continent.name", is("South America")));
    }

    @Test
    void testSaveCountry() throws Exception {
        Country input = new Country(null, "Chile", continent);
        Country saved = new Country(3L, "Chile", continent);

        Mockito.when(countryService.saveCountry(Mockito.any(Country.class))).thenReturn(saved);

        mockMvc.perform(post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Chile")))
                .andExpect(jsonPath("$.continent.name", is("South America")));
    }

    @Test
    void testUpdateCountry() throws Exception {
        Country updated = new Country(1L, "Peru", continent);

        Mockito.when(countryService.updateCountry(Mockito.any(Country.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/countries/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Peru")))
                .andExpect(jsonPath("$.continent.name", is("South America")));
    }

    @Test
    void testDeleteCountrySuccess() throws Exception {
        Mockito.when(countryService.deleteCountry(1L)).thenReturn(true);

        mockMvc.perform(delete("/countries/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Country deleted successfully"));
    }

    @Test
    void testDeleteCountryFail() throws Exception {
        Mockito.when(countryService.deleteCountry(1L)).thenReturn(false);

        mockMvc.perform(delete("/countries/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting country"));
    }

    @Test
    void testSaveCountriesBatch() throws Exception {
        Country input1 = new Country(null, "Ecuador", continent);
        Country input2 = new Country(null, "Uruguay", continent);

        Country saved1 = new Country(1L, "Ecuador", continent);
        Country saved2 = new Country(2L, "Uruguay", continent);

        Mockito.when(countryService.saveCountries(Mockito.any()))
                .thenReturn(new ArrayList<>(Arrays.asList(saved1, saved2)));

        mockMvc.perform(post("/countries/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Arrays.asList(input1, input2))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Ecuador")))
                .andExpect(jsonPath("$[1].name", is("Uruguay")))
                .andExpect(jsonPath("$[0].continent.name", is("South America")));
    }
}
