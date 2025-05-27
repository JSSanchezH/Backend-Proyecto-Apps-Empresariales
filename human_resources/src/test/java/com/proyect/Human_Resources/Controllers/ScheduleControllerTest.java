package com.proyect.Human_Resources.Controllers;

import com.proyect.Human_Resources.controllers.ScheduleController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.models.*;
import com.proyect.Human_Resources.services.AuthService;
import com.proyect.Human_Resources.services.ScheduleService;

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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(
    controllers = ScheduleController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

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
    private final UserCompany userCompany = new UserCompany(1L, company, "user", "pass", "token");
    private final Employee employee = new Employee(1L, "John", "Doe", "john@example.com", "123456", new Date(System.currentTimeMillis()), role, department, "url", true);

    private final Time startTime = Time.valueOf("08:00:00");
    private final Time endTime = Time.valueOf("17:00:00");
    private final Time breakStart = Time.valueOf("12:00:00");
    private final Time breakEnd = Time.valueOf("13:00:00");

    @Test
    void testGetAllSchedules() throws Exception {
        ArrayList<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule(1L, startTime, endTime, breakStart, breakEnd, employee));
        schedules.add(new Schedule(2L, startTime, endTime, breakStart, breakEnd, employee));

        Mockito.when(authService.getAuthenticatedUser(Mockito.any(HttpServletRequest.class))).thenReturn(userCompany);
        Mockito.when(scheduleService.getSchedules(123456789L)).thenReturn(schedules);

        mockMvc.perform(get("/schedules"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(2)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    void testGetScheduleById() throws Exception {
        Schedule schedule = new Schedule(1L, startTime, endTime, breakStart, breakEnd, employee);

        Mockito.when(scheduleService.getScheduleById(1L)).thenReturn(Optional.of(schedule));

        mockMvc.perform(get("/schedules/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.startTime", is("08:00:00")))
            .andExpect(jsonPath("$.endTime", is("17:00:00")))
            .andExpect(jsonPath("$.breakStart", is("12:00:00")))
            .andExpect(jsonPath("$.breakEnd", is("13:00:00")));
    }

    @Test
    void testGetScheduleByIdNotFound() throws Exception {
        Mockito.when(scheduleService.getScheduleById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/schedules/999"))
            .andExpect(status().isOk());
    }

    @Test
    void testSaveSchedule() throws Exception {
        Schedule input = new Schedule(null, startTime, endTime, breakStart, breakEnd, employee);
        Schedule saved = new Schedule(3L, startTime, endTime, breakStart, breakEnd, employee);

        Mockito.when(scheduleService.saveSchedule(Mockito.any(Schedule.class))).thenReturn(saved);

        mockMvc.perform(post("/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(3)));
    }

    @Test
    void testUpdateSchedule() throws Exception {
        Schedule updated = new Schedule(1L, startTime, endTime, breakStart, breakEnd, employee);

        Mockito.when(scheduleService.updateSchedule(Mockito.any(Schedule.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/schedules/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void testDeleteScheduleSuccess() throws Exception {
        Mockito.when(scheduleService.deleteSchedule(1L)).thenReturn(true);

        mockMvc.perform(delete("/schedules/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("Schedule deleted successfully"));
    }

    @Test
    void testDeleteScheduleFail() throws Exception {
        Mockito.when(scheduleService.deleteSchedule(1L)).thenReturn(false);

        mockMvc.perform(delete("/schedules/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("Error deleting schedule"));
    }

    @Test
    void testSaveSchedulesBatch() throws Exception {
        ArrayList<Schedule> inputSchedules = new ArrayList<>();
        inputSchedules.add(new Schedule(null, startTime, endTime, breakStart, breakEnd, employee));
        inputSchedules.add(new Schedule(null, startTime, endTime, breakStart, breakEnd, employee));

        ArrayList<Schedule> savedSchedules = new ArrayList<>();
        savedSchedules.add(new Schedule(4L, startTime, endTime, breakStart, breakEnd, employee));
        savedSchedules.add(new Schedule(5L, startTime, endTime, breakStart, breakEnd, employee));

        Mockito.when(scheduleService.saveSchedules(Mockito.any(ArrayList.class))).thenReturn(savedSchedules);

        mockMvc.perform(post("/schedules/batch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputSchedules)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(2)))
            .andExpect(jsonPath("$[0].id", is(4)))
            .andExpect(jsonPath("$[1].id", is(5)));
    }
    
}
