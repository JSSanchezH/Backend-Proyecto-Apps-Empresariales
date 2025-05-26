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

import com.proyect.Human_Resources.Repositories.IStateRepository;
import com.proyect.Human_Resources.models.State;
import com.proyect.Human_Resources.models.Country;

@ExtendWith(MockitoExtension.class)
public class StateServiceTest {

    @Mock
    private IStateRepository stateRepository;

    @InjectMocks
    private StateService stateService;

    private State state;
    private Country country;
    private ArrayList<State> states;

    @BeforeEach
    void setUp() {
        country = new Country();
        country.setId(1L);
        country.setName("Test Country");

        state = new State();
        state.setId(1L);
        state.setName("Test State");
        state.setCountry(country);

        states = new ArrayList<>();
        states.add(state);
    }

    @Test
    void testGetStates() {
        when(stateRepository.findAll()).thenReturn(states);

        ArrayList<State> result = stateService.getStates();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test State", result.get(0).getName());
        verify(stateRepository).findAll();
    }

    @Test
    void testSaveState() {
        when(stateRepository.save(state)).thenReturn(state);

        State result = stateService.saveState(state);

        assertNotNull(result);
        assertEquals("Test State", result.getName());
        verify(stateRepository).save(state);
    }

    @Test
    void testSaveStates() {
        when(stateRepository.saveAll(states)).thenReturn(states);

        ArrayList<State> result = stateService.saveStates(states);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(stateRepository).saveAll(states);
    }

    @Test
    void testGetStateById() {
        when(stateRepository.findById(1L)).thenReturn(Optional.of(state));

        Optional<State> result = stateService.getStateById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test State", result.get().getName());
        verify(stateRepository).findById(1L);
    }

    @Test
    void testUpdateState() {
        State updatedState = new State();
        updatedState.setName("Updated State");
        updatedState.setCountry(country);

        when(stateRepository.findById(1L)).thenReturn(Optional.of(state));
        when(stateRepository.save(any(State.class))).thenReturn(state);

        State result = stateService.updateState(updatedState, 1L);

        assertNotNull(result);
        verify(stateRepository).findById(1L);
        verify(stateRepository).save(state);
    }

    @Test
    void testDeleteStateSuccess() {
        doNothing().when(stateRepository).deleteById(1L);

        boolean result = stateService.deleteState(1L);

        assertTrue(result);
        verify(stateRepository).deleteById(1L);
    }

    @Test
    void testDeleteStateFailure() {
        doThrow(new RuntimeException()).when(stateRepository).deleteById(1L);

        boolean result = stateService.deleteState(1L);

        assertFalse(result);
        verify(stateRepository).deleteById(1L);
    }
}
    

