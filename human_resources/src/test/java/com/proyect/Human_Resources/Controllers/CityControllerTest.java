package com.proyect.Human_Resources.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.controllers.CityController;
import com.proyect.Human_Resources.models.City;
import com.proyect.Human_Resources.models.Continent;
import com.proyect.Human_Resources.models.Country;
import com.proyect.Human_Resources.models.State;
import com.proyect.Human_Resources.services.CityService;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = CityController.class,
    excludeFilters = {
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class CityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Continent continent = new Continent(1L, "South America");

    private final Country country = new Country(1L, "Colombia", continent);

    private final State state = new State(1L, "Quindío", country);

    @Test
    void testGetAllCities() throws Exception {
        City city1 = new City(1L,"Armenia",state);
        City city2 = new City(2L, "Montenegro", state);

        Mockito.when(cityService.getCities()).thenReturn(new ArrayList<>(Arrays.asList(city1, city2)));

        mockMvc.perform(get("/cities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Armenia")))
                .andExpect(jsonPath("$[0].state.name", is("Quindío")))
                .andExpect(jsonPath("$[1].name", is("Montenegro")));
    }

    @Test
    void testGetCityById() throws Exception {
        City city = new City(1L, "Calarcá", state);
        Mockito.when(cityService.getCityById(1L)).thenReturn(Optional.of(city));

        mockMvc.perform(get("/cities/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Calarcá")))
                .andExpect(jsonPath("$.state.name", is("Quindío")));
    }

    @Test
    void testSaveCity() throws Exception {
        City input = new City(null, "Filandia", state);
        City saved = new City(3L, "Filandia", state);

        Mockito.when(cityService.saveCity(Mockito.any(City.class))).thenReturn(saved);

        mockMvc.perform(post("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Filandia")))
                .andExpect(jsonPath("$.state.name", is("Quindío")));
    }

    @Test
    void testUpdateCity() throws Exception {
        City updated = new City(1L, "Circasia", state);

        Mockito.when(cityService.updateCity(Mockito.any(City.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/cities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Circasia")))
                .andExpect(jsonPath("$.state.name", is("Quindío")));
    }

    @Test
    void testDeleteCitySuccess() throws Exception {
        Mockito.when(cityService.deleteCity(1L)).thenReturn(true);

        mockMvc.perform(delete("/cities/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("City deleted successfully"));
    }

    @Test
    void testDeleteCityFail() throws Exception {
        Mockito.when(cityService.deleteCity(1L)).thenReturn(false);

        mockMvc.perform(delete("/cities/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting city"));
    }

    @Test
    void testSaveCitiesBatch() throws Exception {
        City input1 = new City(null, "Salento", state);
        City input2 = new City(null, "Buenavista", state);

        City saved1 = new City(1L, "Salento", state);
        City saved2 = new City(2L, "Buenavista", state);

        Mockito.when(cityService.saveCities(Mockito.any()))
                .thenReturn(new ArrayList<>(Arrays.asList(saved1, saved2)));

        mockMvc.perform(post("/cities/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Arrays.asList(input1, input2))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Salento")))
                .andExpect(jsonPath("$[1].name", is("Buenavista")))
                .andExpect(jsonPath("$[0].state.name", is("Quindío")));
    }
}
