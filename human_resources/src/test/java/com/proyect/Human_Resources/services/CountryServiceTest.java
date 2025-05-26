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

import com.proyect.Human_Resources.Repositories.ICountryRepository;
import com.proyect.Human_Resources.models.Country;
import com.proyect.Human_Resources.models.Continent;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private ICountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    private Country country;
    private Continent continent;
    private ArrayList<Country> countries;

    @BeforeEach
    void setUp() {
        continent = new Continent();
        continent.setId(1L);
        continent.setName("South America");

        country = new Country();
        country.setId(1L);
        country.setName("Colombia");
        country.setContinent(continent);

        countries = new ArrayList<>();
        countries.add(country);
    }

    @Test
    void testGetCountries() {
        when(countryRepository.findAll()).thenReturn(countries);

        ArrayList<Country> result = countryService.getCountries();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Colombia", result.get(0).getName());
        verify(countryRepository).findAll();
    }

    @Test
    void testSaveCountry() {
        when(countryRepository.save(country)).thenReturn(country);

        Country result = countryService.saveCountry(country);

        assertNotNull(result);
        assertEquals("Colombia", result.getName());
        verify(countryRepository).save(country);
    }

    @Test
    void testSaveCountries() {
        when(countryRepository.saveAll(countries)).thenReturn(countries);

        ArrayList<Country> result = countryService.saveCountries(countries);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(countryRepository).saveAll(countries);
    }

    @Test
    void testGetCountryById() {
        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));

        Optional<Country> result = countryService.getCountryById(1L);

        assertTrue(result.isPresent());
        assertEquals("Colombia", result.get().getName());
        verify(countryRepository).findById(1L);
    }

    @Test
    void testUpdateCountry() {
        Country updatedCountry = new Country();
        updatedCountry.setName("Brazil");
        updatedCountry.setContinent(continent);

        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(countryRepository.save(any(Country.class))).thenReturn(country);

        Country result = countryService.updateCountry(updatedCountry, 1L);

        assertNotNull(result);
        verify(countryRepository).findById(1L);
        verify(countryRepository).save(country);
    }

    @Test
    void testDeleteCountrySuccess() {
        doNothing().when(countryRepository).deleteById(1L);

        boolean result = countryService.deleteCountry(1L);

        assertTrue(result);
        verify(countryRepository).deleteById(1L);
    }

    @Test
    void testDeleteCountryFailure() {
        doThrow(new RuntimeException()).when(countryRepository).deleteById(1L);

        boolean result = countryService.deleteCountry(1L);

        assertFalse(result);
        verify(countryRepository).deleteById(1L);
    }
    
}
