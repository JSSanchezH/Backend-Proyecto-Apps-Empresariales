package com.proyect.Human_Resources.Controllers;

import org.springframework.context.annotation.FilterType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.AbsenceTypeController;
import com.proyect.Human_Resources.models.Absence_Type;
import com.proyect.Human_Resources.services.Absence_TypeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = AbsenceTypeController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class Absence_TypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Absence_TypeService absenceTypeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAbsenceTypes() throws Exception {
        Absence_Type type1 = new Absence_Type();
        type1.setId(1L);
        type1.setName("Vacation");

        Absence_Type type2 = new Absence_Type();
        type2.setId(2L);
        type2.setName("Sick Leave");

        ArrayList<Absence_Type> types = new ArrayList<>(Arrays.asList(type1, type2));
        Mockito.when(absenceTypeService.getAbsenceTypes()).thenReturn(types);

        mockMvc.perform(get("/absence_types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Vacation")))
                .andExpect(jsonPath("$[1].name", is("Sick Leave")));
    }

    @Test
    void testGetAbsenceTypeById() throws Exception {
        Absence_Type type = new Absence_Type();
        type.setId(1L);
        type.setName("Vacation");

        Mockito.when(absenceTypeService.getAbsenceTypeById(1L)).thenReturn(Optional.of(type));

        mockMvc.perform(get("/absence_types/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Vacation")));
    }

    @Test
    void testSaveAbsenceType() throws Exception {
        Absence_Type input = new Absence_Type();
        input.setName("Parental Leave");

        Absence_Type saved = new Absence_Type();
        saved.setId(10L);
        saved.setName("Parental Leave");

        Mockito.when(absenceTypeService.saveAbsenceType(Mockito.any(Absence_Type.class))).thenReturn(saved);

        mockMvc.perform(post("/absence_types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("Parental Leave")));
    }

    @Test
    void testUpdateAbsenceType() throws Exception {
        Absence_Type updated = new Absence_Type();
        updated.setId(1L);
        updated.setName("Updated Type");

        Mockito.when(absenceTypeService.updateAbsenceType(Mockito.any(Absence_Type.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/absence_types/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Type")));
    }

    @Test
    void testDeleteAbsenceTypeSuccess() throws Exception {
        Mockito.when(absenceTypeService.deleteAbsenceType(1L)).thenReturn(true);

        mockMvc.perform(delete("/absence_types/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Absence type deleted successfully"));
    }

    
    @Test
    void testDeleteAbsenceTypeFail() throws Exception {
        Mockito.when(absenceTypeService.deleteAbsenceType(1L)).thenReturn(false);

        mockMvc.perform(delete("/absence_types/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting absence type"));
    }

    @Test
    void testSaveAbsenceTypesBatch() throws Exception {
        Absence_Type type1 = new Absence_Type();
        type1.setName("Leave A");

        Absence_Type type2 = new Absence_Type();
        type2.setName("Leave B");

        ArrayList<Absence_Type> inputList = new ArrayList<>(Arrays.asList(type1, type2));

        Absence_Type saved1 = new Absence_Type();
        saved1.setId(1L);
        saved1.setName("Leave A");

        Absence_Type saved2 = new Absence_Type();
        saved2.setId(2L);
        saved2.setName("Leave B");

        ArrayList<Absence_Type> savedList = new ArrayList<>(Arrays.asList(saved1, saved2));

        Mockito.when(absenceTypeService.saveAbsenceTypes(Mockito.any())).thenReturn(savedList);

        mockMvc.perform(post("/absence_types/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }
    
}
