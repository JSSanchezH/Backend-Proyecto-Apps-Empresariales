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

import com.proyect.Human_Resources.Repositories.ICityRepository;
import com.proyect.Human_Resources.models.City;
import com.proyect.Human_Resources.models.State;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private ICityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    private City city;
    private State state;
    private ArrayList<City> cities;

    @BeforeEach
    void setUp() {
        state = new State();
        state.setId(1L);
        state.setName("Test State");

        city = new City();
        city.setId(1L);
        city.setName("Test City");
        city.setState(state);

        cities = new ArrayList<>();
        cities.add(city);
    }

    @Test
    void testGetCities() {
        when(cityRepository.findAll()).thenReturn(cities);

        ArrayList<City> result = cityService.getCities();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test City", result.get(0).getName());
        verify(cityRepository).findAll();
    }

    @Test
    void testSaveCity() {
        when(cityRepository.save(city)).thenReturn(city);

        City result = cityService.saveCity(city);

        assertNotNull(result);
        assertEquals("Test City", result.getName());
        verify(cityRepository).save(city);
    }

    @Test
    void testSaveCities() {
        when(cityRepository.saveAll(cities)).thenReturn(cities);

        ArrayList<City> result = cityService.saveCities(cities);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(cityRepository).saveAll(cities);
    }

    @Test
    void testGetCityById() {
        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        Optional<City> result = cityService.getCityById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test City", result.get().getName());
        verify(cityRepository).findById(1L);
    }

    @Test
    void testUpdateCity() {
        City updatedCity = new City();
        updatedCity.setName("Updated City");
        updatedCity.setState(state);

        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        when(cityRepository.save(any(City.class))).thenReturn(city);

        City result = cityService.updateCity(updatedCity, 1L);

        assertNotNull(result);
        verify(cityRepository).findById(1L);
        verify(cityRepository).save(city);
    }

    @Test
    void testDeleteCitySuccess() {
        doNothing().when(cityRepository).deleteById(1L);

        boolean result = cityService.deleteCity(1L);

        assertTrue(result);
        verify(cityRepository).deleteById(1L);
    }

    @Test
    void testDeleteCityFailure() {
        doThrow(new RuntimeException()).when(cityRepository).deleteById(1L);

        boolean result = cityService.deleteCity(1L);

        assertFalse(result);
        verify(cityRepository).deleteById(1L);
    }
    
}
