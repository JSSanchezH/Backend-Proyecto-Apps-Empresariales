package com.proyect.Human_Resources.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.EvaluationController;
import com.proyect.Human_Resources.models.*;
import com.proyect.Human_Resources.services.AuthService;
import com.proyect.Human_Resources.services.EvaluationService;

import jakarta.servlet.http.HttpServletRequest;
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
    controllers = EvaluationController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class EvaluationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvaluationService evaluationService;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Continent continent = new Continent(1L, "South America");
    private final Country country = new Country(1L, "Colombia", continent);
    private final State state = new State(1L, "Quindío", country);
    private final City city = new City(1L, "Armenia", state);
    private final Company company = new Company(1L, "Tech Corp", 123456789L, "123 Main St", "info@techcorp.com", "Technology", "http://logo.url");
    private final Headquarter headquarter = new Headquarter(1L, "Main Office", "456 Business Ave", 5551234567L, city, company);
    private final Department department = new Department(1L, "IT", headquarter);
    private final Role role = new Role(1L, "Developer");
    private final Employee employee = new Employee(1L, "John", "Doe", "john@example.com", "123456", new Date(System.currentTimeMillis()), role, department, "url", true);
    private final UserCompany userCompany = new UserCompany(1L, company, "user", "pass", "token");

    @Test
    void testGetAllEvaluations() throws Exception {
        ArrayList<Evaluation> evaluations = new ArrayList<>();
        evaluations.add(new Evaluation(1L, employee, new Date(System.currentTimeMillis()), 8, 9, 10, 7, 6, 9));
        evaluations.add(new Evaluation(2L, employee, new Date(System.currentTimeMillis()), 7, 8, 9, 6, 5, 8));

        Mockito.when(authService.getAuthenticatedUser(Mockito.any(HttpServletRequest.class))).thenReturn(userCompany);

        Mockito.when(evaluationService.getEvaluations(123456789L)).thenReturn(evaluations);

        mockMvc.perform(get("/evaluations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].punctuality", is(8)))
                .andExpect(jsonPath("$[1].proactivity", is(8)));
    }

    @Test
    void testGetEvaluationById() throws Exception {
        Evaluation eval = new Evaluation(1L, employee, new Date(System.currentTimeMillis()), 8, 9, 10, 7, 6, 9);

        Mockito.when(evaluationService.getEvaluationById(1L)).thenReturn(Optional.of(eval));

        mockMvc.perform(get("/evaluations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.punctuality", is(8)));
    }

    @Test
    void testSaveEvaluation() throws Exception {
        Evaluation input = new Evaluation(null, employee, new Date(System.currentTimeMillis()), 7, 8, 9, 6, 7, 8);
        Evaluation saved = new Evaluation(3L, employee, input.getEvaluationDate(), 7, 8, 9, 6, 7, 8);

        Mockito.when(evaluationService.saveEvaluation(Mockito.any(Evaluation.class))).thenReturn(saved);

        mockMvc.perform(post("/evaluations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.performance", is(8)));
    }

    @Test
    void testUpdateEvaluation() throws Exception {
        Evaluation updated = new Evaluation(1L, employee, new Date(System.currentTimeMillis()), 6, 7, 8, 5, 6, 7);

        Mockito.when(evaluationService.updateEvaluation(Mockito.any(Evaluation.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/evaluations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courtesy", is(8)));
    }

    @Test
    void testDeleteEvaluationSuccess() throws Exception {
        Mockito.when(evaluationService.deleteEvaluation(1L)).thenReturn(true);

        mockMvc.perform(delete("/evaluations/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Evaluation deleted successfully"));
    }

    @Test
    void testDeleteEvaluationFail() throws Exception {
        Mockito.when(evaluationService.deleteEvaluation(1L)).thenReturn(false);

        mockMvc.perform(delete("/evaluations/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting evaluation"));
    }

    @Test
    void testSaveEvaluationsBatch() throws Exception {
        ArrayList<Evaluation> inputEvaluations = new ArrayList<>();
        inputEvaluations.add(new Evaluation(null, employee, new Date(System.currentTimeMillis()), 7, 8, 7, 7, 6, 7));
        inputEvaluations.add(new Evaluation(null, employee, new Date(System.currentTimeMillis()), 9, 9, 9, 9, 9, 9));

        ArrayList<Evaluation> savedEvaluations = new ArrayList<>();
        savedEvaluations.add(new Evaluation(4L, employee, inputEvaluations.get(0).getEvaluationDate(), 7, 8, 7, 7, 6, 7));
        savedEvaluations.add(new Evaluation(5L, employee, inputEvaluations.get(1).getEvaluationDate(), 9, 9, 9, 9, 9, 9));

        Mockito.when(evaluationService.saveEvaluations(Mockito.any(ArrayList.class))).thenReturn(savedEvaluations);

        mockMvc.perform(post("/evaluations/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputEvaluations)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].precision", is(7)))
                .andExpect(jsonPath("$[1].proactivity", is(9)));
    }
    
}
