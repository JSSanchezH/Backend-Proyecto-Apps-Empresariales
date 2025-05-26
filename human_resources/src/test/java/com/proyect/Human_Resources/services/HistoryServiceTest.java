package com.proyect.Human_Resources.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyect.Human_Resources.Repositories.IHistoryRepository;
import com.proyect.Human_Resources.models.History;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceTest {

    @Mock
    private IHistoryRepository historyRepository;

    @InjectMocks
    private HistoryService historyService;

    private History history;
    private ArrayList<History> historyList;

    @BeforeEach
    void setUp() {
        history = new History();
        history.setId(1L);
        history.setEndDate(Date.valueOf("2025-05-26"));
        history.setReason("Updated reason");

        historyList = new ArrayList<>();
        historyList.add(history);
    }

    @Test
    void testGetHistories() {
        when(historyRepository.findAll()).thenReturn(historyList);

        ArrayList<History> result = historyService.getHistories();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(historyRepository).findAll();
    }

    @Test
    void testSaveHistory() {
        when(historyRepository.save(history)).thenReturn(history);

        History saved = historyService.saveHistory(history);

        assertNotNull(saved);
        verify(historyRepository).save(history);
    }

    @Test
    void testSaveHistories() {
        when(historyRepository.saveAll(historyList)).thenReturn(historyList);

        ArrayList<History> savedList = historyService.saveHistories(historyList);

        assertNotNull(savedList);
        assertEquals(1, savedList.size());
        verify(historyRepository).saveAll(historyList);
    }

    @Test
    void testGetHistoryById_Found() {
        when(historyRepository.findById(1L)).thenReturn(Optional.of(history));

        Optional<History> found = historyService.getHistoryById(1L);

        assertTrue(found.isPresent());
        assertEquals(history, found.get());
        verify(historyRepository).findById(1L);
    }

    @Test
    void testGetHistoryById_NotFound() {
        when(historyRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<History> found = historyService.getHistoryById(1L);

        assertFalse(found.isPresent());
        verify(historyRepository).findById(1L);
    }

    @Test
    void testUpdateHistory() {
        History updatedData = new History();
        updatedData.setEndDate(Date.valueOf("2025-06-30"));
        updatedData.setReason("New reason");

        when(historyRepository.findById(1L)).thenReturn(Optional.of(history));
        when(historyRepository.save(any(History.class))).thenAnswer(invocation -> invocation.getArgument(0));

        History updated = historyService.updateHistory(updatedData, 1L);

        assertEquals(Date.valueOf("2025-06-30"), updated.getEndDate());
        assertEquals("New reason", updated.getReason());

        verify(historyRepository).findById(1L);
        verify(historyRepository).save(any(History.class));
    }

    @Test
    void testDeleteHistory_Success() {
        doNothing().when(historyRepository).deleteById(1L);

        boolean result = historyService.deleteHistory(1L);

        assertTrue(result);
        verify(historyRepository).deleteById(1L);
    }

    @Test
    void testDeleteHistory_Failure() {
        doThrow(new RuntimeException("Error deleting")).when(historyRepository).deleteById(1L);

        boolean result = historyService.deleteHistory(1L);

        assertFalse(result);
        verify(historyRepository).deleteById(1L);
    }


    
}
