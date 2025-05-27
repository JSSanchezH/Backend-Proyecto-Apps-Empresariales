package com.proyect.Human_Resources.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.HistoryController;
import com.proyect.Human_Resources.models.*;
import com.proyect.Human_Resources.services.HistoryService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = HistoryController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;

    @Autowired
    private ObjectMapper objectMapper;

    // Estructura jerárquica para el test
    private final Continent continent = new Continent(1L, "South America");
    private final Country country = new Country(1L, "Colombia", continent);
    private final State state = new State(1L, "Quindío", country);
    private final City city = new City(1L, "Armenia", state);
    private final Company company = new Company(1L, "Tech Corp", 123456789L, "123 Main St", "info@techcorp.com", "Technology", "http://logo.url");
    private final Headquarter headquarter = new Headquarter(1L, "Main Office", "456 Business Ave", 5551234567L, city, company);
    private final Department department = new Department(1L, "IT", headquarter);
    private final Role role = new Role(1L, "Developer");
    private final Employee employee = new Employee(1L, "John", "Doe", "john@example.com", "123456", new Date(System.currentTimeMillis()), role, department, "url", true);
    

    @Test
    void testGetAllHistories() throws Exception {
        ArrayList<History> histories = new ArrayList<>();
        histories.add(new History(1L, employee, new Date(System.currentTimeMillis()), "Resignation"));
        histories.add(new History(2L, employee, new Date(System.currentTimeMillis()), "Termination"));

        Mockito.when(historyService.getHistories()).thenReturn(histories);

        mockMvc.perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].reason", is("Resignation")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].reason", is("Termination")));
    }

    @Test
    void testGetHistoryById() throws Exception {
        History history = new History(1L, employee, new Date(System.currentTimeMillis()), "Resignation");

        Mockito.when(historyService.getHistoryById(1L)).thenReturn(Optional.of(history));

        mockMvc.perform(get("/history/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.reason", is("Resignation")));
    }

    @Test
    void testGetHistoryByIdNotFound() throws Exception {
        Mockito.when(historyService.getHistoryById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/history/999"))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveHistory() throws Exception {
        History input = new History(7L, employee, new Date(System.currentTimeMillis()), "New Resignation");
        History saved = new History(3L, employee, new Date(System.currentTimeMillis()), "New Resignation");

        Mockito.when(historyService.saveHistory(Mockito.any(History.class))).thenReturn(saved);

        mockMvc.perform(post("/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.reason", is("New Resignation")));
    }

    @Test
    void testUpdateHistory() throws Exception {
        History updated = new History(1L, employee, new Date(System.currentTimeMillis()), "Updated Reason");

        Mockito.when(historyService.updateHistory(Mockito.any(History.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/history/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.reason", is("Updated Reason")));
    }

    @Test
    void testDeleteHistorySuccess() throws Exception {
        Mockito.when(historyService.deleteHistory(1L)).thenReturn(true);

        mockMvc.perform(delete("/history/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("History deleted successfully"));
    }

    @Test
    void testDeleteHistoryFail() throws Exception {
        Mockito.when(historyService.deleteHistory(1L)).thenReturn(false);

        mockMvc.perform(delete("/history/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting history"));
    }

    @Test
    void testSaveHistoriesBatch() throws Exception {
        ArrayList<History> inputHistories = new ArrayList<>();
        inputHistories.add(new History(1L, employee, new Date(System.currentTimeMillis()), "Batch Resignation 1"));
        inputHistories.add(new History(2L, employee, new Date(System.currentTimeMillis()), "Batch Resignation 2"));

        ArrayList<History> savedHistories = new ArrayList<>();
        savedHistories.add(new History(4L, employee, new Date(System.currentTimeMillis()), "Batch Resignation 1"));
        savedHistories.add(new History(5L, employee, new Date(System.currentTimeMillis()), "Batch Resignation 2"));

        Mockito.when(historyService.saveHistories(Mockito.any(ArrayList.class))).thenReturn(savedHistories);

        mockMvc.perform(post("/history/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputHistories)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(4)))
                .andExpect(jsonPath("$[0].reason", is("Batch Resignation 1")))
                .andExpect(jsonPath("$[1].id", is(5)))
                .andExpect(jsonPath("$[1].reason", is("Batch Resignation 2")));
    }
    
}
