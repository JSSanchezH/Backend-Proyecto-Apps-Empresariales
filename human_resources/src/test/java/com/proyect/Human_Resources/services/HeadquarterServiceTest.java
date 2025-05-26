package com.proyect.Human_Resources.services;
import com.proyect.Human_Resources.models.City;
import com.proyect.Human_Resources.models.Headquarter;
import com.proyect.Human_Resources.Repositories.IHeadquarterRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeadquarterServiceTest {

    @Mock
    private IHeadquarterRepository headquarterRepository;

    @InjectMocks
    private HeadquarterService headquarterService;

    private Headquarter headquarter;
    private City city;
    private ArrayList<Headquarter> headquarterList;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setId(2L);
        city.setName("Bogotá");

        headquarter = new Headquarter();
        headquarter.setId(1L);
        headquarter.setName("Main HQ");
        headquarter.setPhone(123456789L);  
        headquarter.setCity(city);

        headquarterList = new ArrayList<>();
        headquarterList.add(headquarter);
    }

    @Test
    void testGetHeadquarters() {
        long nit = 123456789L;
        when(headquarterRepository.findByCompanyNit(nit)).thenReturn(headquarterList);

        ArrayList<Headquarter> result = headquarterService.getHeadquarters(nit);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(headquarterRepository).findByCompanyNit(nit);
    }

    @Test
    void testSaveHeadquarter() {
        when(headquarterRepository.save(headquarter)).thenReturn(headquarter);

        Headquarter saved = headquarterService.saveHeadquarter(headquarter);

        assertNotNull(saved);
        verify(headquarterRepository).save(headquarter);
    }

    @Test
    void testSaveHeadquarters() {
        when(headquarterRepository.saveAll(headquarterList)).thenReturn(headquarterList);

        ArrayList<Headquarter> savedList = headquarterService.saveHeadquarters(headquarterList);

        assertNotNull(savedList);
        assertEquals(1, savedList.size());
        verify(headquarterRepository).saveAll(headquarterList);
    }

    @Test
    void testGetHeadquarterById_Found() {
        when(headquarterRepository.findById(1L)).thenReturn(Optional.of(headquarter));

        Optional<Headquarter> found = headquarterService.getHeadquarterById(1L);

        assertTrue(found.isPresent());
        assertEquals(headquarter, found.get());
        verify(headquarterRepository).findById(1L);
    }

    @Test
    void testGetHeadquarterById_NotFound() {
        when(headquarterRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Headquarter> found = headquarterService.getHeadquarterById(1L);

        assertFalse(found.isPresent());
        verify(headquarterRepository).findById(1L);
    }

    @Test
    void testUpdateHeadquarter() {
        Headquarter updatedData = new Headquarter();
        updatedData.setName("New HQ");
        updatedData.setPhone(987654321L);  
        updatedData.setCity(city);

        when(headquarterRepository.findById(1L)).thenReturn(Optional.of(headquarter));
        when(headquarterRepository.save(any(Headquarter.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Headquarter updated = headquarterService.updateHeadquarter(updatedData, 1L);

        assertEquals("New HQ", updated.getName());
        assertEquals(Long.valueOf(987654321), updated.getPhone()); 
        assertEquals(city, updated.getCity()); 

        verify(headquarterRepository).findById(1L);
        verify(headquarterRepository).save(any(Headquarter.class));
    }

    @Test
    void testDeleteHeadquarter_Success() {
        doNothing().when(headquarterRepository).deleteById(1L);

        boolean result = headquarterService.deleteHeadquarter(1L);

        assertTrue(result);
        verify(headquarterRepository).deleteById(1L);
    }

    @Test
    void testDeleteHeadquarter_Failure() {
        doThrow(new RuntimeException("Error deleting")).when(headquarterRepository).deleteById(1L);

        boolean result = headquarterService.deleteHeadquarter(1L);

        assertFalse(result);
        verify(headquarterRepository).deleteById(1L);
    }
    
}
