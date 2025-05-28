package com.proyect.Human_Resources.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyect.Human_Resources.Repositories.IAbsenceTypeRepository;
import com.proyect.Human_Resources.models.Absence_Type;

@ExtendWith(MockitoExtension.class)
public class Absence_TypeServiceTest {

    @Mock
    private IAbsenceTypeRepository absenceTypeRepository;

    @InjectMocks
    private Absence_TypeService absenceTypeService;

    private Absence_Type absenceType;
    private ArrayList<Absence_Type> absenceTypes;

    @BeforeEach
    void setUp() {
        absenceType = new Absence_Type();
        absenceType.setId(1L);
        absenceType.setName("Sick Leave");

        absenceTypes = new ArrayList<>();
        absenceTypes.add(absenceType);
    }

    @Test
    void testGetAbsenceTypes() {
        when(absenceTypeRepository.findAll()).thenReturn(absenceTypes);

        ArrayList<Absence_Type> result = absenceTypeService.getAbsenceTypes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Sick Leave", result.get(0).getName());
        verify(absenceTypeRepository).findAll();
    }

    @Test
    void testSaveAbsenceType() {
        when(absenceTypeRepository.save(absenceType)).thenReturn(absenceType);

        Absence_Type result = absenceTypeService.saveAbsenceType(absenceType);

        assertNotNull(result);
        assertEquals("Sick Leave", result.getName());
        verify(absenceTypeRepository).save(absenceType);
    }

    @Test
    void testSaveAbsenceTypes() {
        when(absenceTypeRepository.saveAll(absenceTypes)).thenReturn(absenceTypes);

        ArrayList<Absence_Type> result = absenceTypeService.saveAbsenceTypes(absenceTypes);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(absenceTypeRepository).saveAll(absenceTypes);
    }

    @Test
    void testGetAbsenceTypeById() {
        when(absenceTypeRepository.findById(1L)).thenReturn(Optional.of(absenceType));

        Optional<Absence_Type> result = absenceTypeService.getAbsenceTypeById(1L);

        assertTrue(result.isPresent());
        assertEquals("Sick Leave", result.get().getName());
        verify(absenceTypeRepository).findById(1L);
    }

    @Test
    void testUpdateAbsenceType() {
        Absence_Type updatedAbsenceType = new Absence_Type();
        updatedAbsenceType.setName("Vacation");

        when(absenceTypeRepository.findById(1L)).thenReturn(Optional.of(absenceType));
        when(absenceTypeRepository.save(any(Absence_Type.class))).thenReturn(absenceType);

        Absence_Type result = absenceTypeService.updateAbsenceType(updatedAbsenceType, 1L);

        assertNotNull(result);
        verify(absenceTypeRepository).findById(1L);
        verify(absenceTypeRepository).save(absenceType);
    }

    @Test
    void testDeleteAbsenceTypeSuccess() {
        doNothing().when(absenceTypeRepository).deleteById(1L);

        boolean result = absenceTypeService.deleteAbsenceType(1L);

        assertTrue(result);
        verify(absenceTypeRepository).deleteById(1L);
    }

    @Test
    void testDeleteAbsenceTypeFailure() {
        doThrow(new RuntimeException()).when(absenceTypeRepository).deleteById(1L);

        boolean result = absenceTypeService.deleteAbsenceType(1L);

        assertFalse(result);
        verify(absenceTypeRepository).deleteById(1L);
    }
    
}
