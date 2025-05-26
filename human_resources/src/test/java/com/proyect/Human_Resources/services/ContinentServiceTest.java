package com.proyect.Human_Resources.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyect.Human_Resources.Repositories.IContinentRepository;
import com.proyect.Human_Resources.models.Continent;

@ExtendWith(MockitoExtension.class)
public class ContinentServiceTest {

    @Mock
    private IContinentRepository continentRepository;

    @InjectMocks
    private ContinentService continentService;

    private Continent continent;
    private ArrayList<Continent> continents;

    @BeforeEach
    void setUp() {
        continent = new Continent();
        continent.setId(1L);
        continent.setName("South America");

        continents = new ArrayList<>();
        continents.add(continent);
    }

    @Test
    void testGetContinents() {
        when(continentRepository.findAll()).thenReturn(continents);

        ArrayList<Continent> result = continentService.getContinents();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("South America", result.get(0).getName());
        verify(continentRepository).findAll();
    }

    @Test
    void testSaveContinent() {
        when(continentRepository.save(continent)).thenReturn(continent);

        Continent result = continentService.saveContinent(continent);

        assertNotNull(result);
        assertEquals("South America", result.getName());
        verify(continentRepository).save(continent);
    }

    @Test
    void testSaveContinents() {
        List<Continent> continentsList = List.of(continent);
        when(continentRepository.saveAll(continentsList)).thenReturn(continentsList);

        List<Continent> result = continentService.saveContinents(continentsList);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(continentRepository).saveAll(continentsList);
    }

    @Test
    void testGetContinentById() {
        when(continentRepository.findById(1L)).thenReturn(Optional.of(continent));

        Optional<Continent> result = continentService.getContinentById(1L);

        assertTrue(result.isPresent());
        assertEquals("South America", result.get().getName());
        verify(continentRepository).findById(1L);
    }

    @Test
    void testUpdateContinent() {
        Continent updatedContinent = new Continent();
        updatedContinent.setName("North America");

        when(continentRepository.findById(1L)).thenReturn(Optional.of(continent));
        when(continentRepository.save(any(Continent.class))).thenReturn(continent);

        Continent result = continentService.updateContinent(updatedContinent, 1L);

        assertNotNull(result);
        verify(continentRepository).findById(1L);
        verify(continentRepository).save(continent);
    }

    @Test
    void testDeleteContinentSuccess() {
        doNothing().when(continentRepository).deleteById(1L);

        boolean result = continentService.deleteContinent(1L);

        assertTrue(result);
        verify(continentRepository).deleteById(1L);
    }

    @Test
    void testDeleteContinentFailure() {
        doThrow(new RuntimeException()).when(continentRepository).deleteById(1L);

        boolean result = continentService.deleteContinent(1L);

        assertFalse(result);
        verify(continentRepository).deleteById(1L);
    }
    
}
