package com.proyect.Human_Resources.services;
import com.proyect.Human_Resources.models.Evaluation;
import com.proyect.Human_Resources.Repositories.IEvaluationRepository;

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
public class EvaluationServiceTest {

    @Mock
    private IEvaluationRepository evaluationRepository;

    @InjectMocks
    private EvaluationService evaluationService;

    private Evaluation evaluation;
    private ArrayList<Evaluation> evaluationList;

    @BeforeEach
    void setUp() {
        evaluation = new Evaluation();
        evaluation.setId(1L);
        evaluationList = new ArrayList<>();
        evaluationList.add(evaluation);
    }

    @Test
    void testGetEvaluations() {
        long nit = 123456789L;
        when(evaluationRepository.findByEmployeeDepartmentHeadquarterCompanyNit(nit)).thenReturn(evaluationList);

        ArrayList<Evaluation> result = evaluationService.getEvaluations(nit);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(evaluationRepository).findByEmployeeDepartmentHeadquarterCompanyNit(nit);
    }

    @Test
    void testSaveEvaluation() {
        when(evaluationRepository.save(evaluation)).thenReturn(evaluation);

        Evaluation saved = evaluationService.saveEvaluation(evaluation);

        assertNotNull(saved);
        verify(evaluationRepository).save(evaluation);
    }

    @Test
    void testSaveEvaluations() {
        when(evaluationRepository.saveAll(evaluationList)).thenReturn(evaluationList);

        ArrayList<Evaluation> savedList = evaluationService.saveEvaluations(evaluationList);

        assertNotNull(savedList);
        assertEquals(1, savedList.size());
        verify(evaluationRepository).saveAll(evaluationList);
    }

    @Test
    void testGetEvaluationById_Found() {
        when(evaluationRepository.findById(1L)).thenReturn(Optional.of(evaluation));

        Optional<Evaluation> found = evaluationService.getEvaluationById(1L);

        assertTrue(found.isPresent());
        assertEquals(evaluation, found.get());
        verify(evaluationRepository).findById(1L);
    }

    @Test
    void testGetEvaluationById_NotFound() {
        when(evaluationRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Evaluation> found = evaluationService.getEvaluationById(1L);

        assertFalse(found.isPresent());
        verify(evaluationRepository).findById(1L);
    }

    @Test
    void testUpdateEvaluation() {
        Evaluation updatedData = new Evaluation();
        updatedData.setPunctuality(4);
        updatedData.setPerformance(5);

        when(evaluationRepository.findById(1L)).thenReturn(Optional.of(evaluation));
        when(evaluationRepository.save(any(Evaluation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Evaluation updated = evaluationService.updateEvaluation(updatedData, 1L);

        assertEquals(4, updated.getPunctuality());
        assertEquals(5, updated.getPerformance());
        verify(evaluationRepository).findById(1L);
        verify(evaluationRepository).save(any(Evaluation.class));
    }

    @Test
    void testDeleteEvaluation_Success() {
        doNothing().when(evaluationRepository).deleteById(1L);

        boolean result = evaluationService.deleteEvaluation(1L);

        assertTrue(result);
        verify(evaluationRepository).deleteById(1L);
    }

    @Test
    void testDeleteEvaluation_Failure() {
        doThrow(new RuntimeException("Error deleting")).when(evaluationRepository).deleteById(1L);

        boolean result = evaluationService.deleteEvaluation(1L);

        assertFalse(result);
        verify(evaluationRepository).deleteById(1L);
    }
    
}
