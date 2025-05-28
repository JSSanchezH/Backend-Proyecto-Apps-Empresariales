package com.proyect.Human_Resources.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.EmployeeController;
import com.proyect.Human_Resources.models.City;
import com.proyect.Human_Resources.models.Company;
import com.proyect.Human_Resources.models.Continent;
import com.proyect.Human_Resources.models.Country;
import com.proyect.Human_Resources.models.Department;
import com.proyect.Human_Resources.models.Employee;
import com.proyect.Human_Resources.models.Headquarter;
import com.proyect.Human_Resources.models.Role;
import com.proyect.Human_Resources.models.State;
import com.proyect.Human_Resources.models.UserCompany;
import com.proyect.Human_Resources.services.AuthService;

import com.proyect.Human_Resources.services.EmployeeService;

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

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(
    controllers = EmployeeController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

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

    @Test
    void testGetAllEmployees() throws Exception {
        Employee emp1 = new Employee(1L, "John", "Doe", "john@example.com", "123456", new Date(System.currentTimeMillis()), role, department, "url", true);
        Employee emp2 = new Employee(2L, "Jane", "Smith", "jane@example.com", "789012", new Date(System.currentTimeMillis()), role, department, "url", true);

        Mockito.when(authService.getAuthenticatedUser(Mockito.any(HttpServletRequest.class))).thenReturn(userCompany);
        Mockito.when(employeeService.getEmployeesByCompanyNit(123456789L)).thenReturn(new ArrayList<>(List.of(emp1, emp2)));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].firstname", is("John")))
                .andExpect(jsonPath("$[1].firstname", is("Jane")));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee employee = new Employee(1L, "John", "Doe", "john@example.com", "123456", new Date(System.currentTimeMillis()), role, department, "url", true);
        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is("John")));
    }

    @Test
    void testSaveEmployee() throws Exception {
        Employee input = new Employee(null, "Alice", "Brown", "alice@example.com", "555555", new Date(System.currentTimeMillis()), role, department, "url", true);
        Employee saved = new Employee(3L, "Alice", "Brown", "alice@example.com", "555555", new Date(System.currentTimeMillis()), role, department, "url", true);

        Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class))).thenReturn(saved);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.firstname", is("Alice")));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee updated = new Employee(1L, "Bob", "Gray", "bob@example.com", "999999", new Date(System.currentTimeMillis()), role, department, "url", false);

        Mockito.when(employeeService.updateEmployee(Mockito.any(Employee.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is("Bob")))
                .andExpect(jsonPath("$.status", is(false)));
    }

    @Test
    void testDeleteEmployeeSuccess() throws Exception {
        Mockito.when(employeeService.deleteEmployee(1L)).thenReturn(true);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee deleted successfully"));
    }

    @Test
    void testDeleteEmployeeFail() throws Exception {
        Mockito.when(employeeService.deleteEmployee(1L)).thenReturn(false);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting employee"));
    }

    @Test
    void testSaveEmployeesBatch() throws Exception {
        Employee input1 = new Employee(null, "Charlie", "Day", "charlie@example.com", "111111", new Date(System.currentTimeMillis()), role, department, "url", true);
        Employee input2 = new Employee(null, "Dennis", "Reynolds", "dennis@example.com", "222222", new Date(System.currentTimeMillis()), role, department, "url", true);

        Employee saved1 = new Employee(4L, "Charlie", "Day", "charlie@example.com", "111111", new Date(System.currentTimeMillis()), role, department, "url", true);
        Employee saved2 = new Employee(5L, "Dennis", "Reynolds", "dennis@example.com", "222222", new Date(System.currentTimeMillis()), role, department, "url", true);

        Mockito.when(employeeService.saveEmployees(Mockito.any())).thenReturn(new ArrayList<>(List.of(saved1, saved2)));

        mockMvc.perform(post("/employees/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(input1, input2))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].firstname", is("Charlie")))
                .andExpect(jsonPath("$[1].firstname", is("Dennis")));
    }
    
}
