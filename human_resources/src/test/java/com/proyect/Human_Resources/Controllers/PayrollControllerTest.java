package com.proyect.Human_Resources.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.PayrollControler;
import com.proyect.Human_Resources.models.*;
import com.proyect.Human_Resources.services.AuthService;
import com.proyect.Human_Resources.services.PayrollService;

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
    controllers = PayrollControler.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class PayrollControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PayrollService payrollService;

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
    private final Payment_Method paymentMethod = new Payment_Method(1L, "Bank Transfer");

    @Test
    void testGetAllPayrolls() throws Exception {
        ArrayList<Payroll> payrolls = new ArrayList<>();
        payrolls.add(new Payroll(1L, employee, new Date(System.currentTimeMillis()), 50000.0, 5000.0, 55000.0, paymentMethod));
        payrolls.add(new Payroll(2L, employee, new Date(System.currentTimeMillis()), 60000.0, 7000.0, 67000.0, paymentMethod));

        Mockito.when(authService.getAuthenticatedUser(Mockito.any(HttpServletRequest.class))).thenReturn(userCompany);
        Mockito.when(payrollService.getPayrolls(123456789L)).thenReturn(payrolls);

        mockMvc.perform(get("/payrolls"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].baseSalary", is(50000.0)))
                .andExpect(jsonPath("$[0].bonuses", is(5000.0)))
                .andExpect(jsonPath("$[0].totalPayment", is(55000.0)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].baseSalary", is(60000.0)))
                .andExpect(jsonPath("$[1].bonuses", is(7000.0)))
                .andExpect(jsonPath("$[1].totalPayment", is(67000.0)));
    }

    @Test
    void testGetPayrollById() throws Exception {
        Payroll payroll = new Payroll(1L, employee, new Date(System.currentTimeMillis()), 50000.0, 5000.0, 55000.0, paymentMethod);

        Mockito.when(payrollService.getPayrollById(1L)).thenReturn(Optional.of(payroll));

        mockMvc.perform(get("/payrolls/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.baseSalary", is(50000.0)))
                .andExpect(jsonPath("$.bonuses", is(5000.0)))
                .andExpect(jsonPath("$.totalPayment", is(55000.0)));
    }

    @Test
    void testGetPayrollByIdNotFound() throws Exception {
        Mockito.when(payrollService.getPayrollById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/payrolls/999"))
                .andExpect(status().isOk());
    }

    @Test
    void testSavePayroll() throws Exception {
        Payroll input = new Payroll(7L, employee, new Date(System.currentTimeMillis()), 45000.0, 3000.0, 48000.0, paymentMethod);
        Payroll saved = new Payroll(3L, employee, new Date(System.currentTimeMillis()), 45000.0, 3000.0, 48000.0, paymentMethod);

        Mockito.when(payrollService.savePayroll(Mockito.any(Payroll.class))).thenReturn(saved);

        mockMvc.perform(post("/payrolls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.baseSalary", is(45000.0)))
                .andExpect(jsonPath("$.bonuses", is(3000.0)))
                .andExpect(jsonPath("$.totalPayment", is(48000.0)));
    }

    @Test
    void testUpdatePayroll() throws Exception {
        Payroll updated = new Payroll(1L, employee, new Date(System.currentTimeMillis()), 55000.0, 8000.0, 63000.0, paymentMethod);

        Mockito.when(payrollService.updatePayroll(Mockito.any(Payroll.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/payrolls/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.baseSalary", is(55000.0)))
                .andExpect(jsonPath("$.bonuses", is(8000.0)))
                .andExpect(jsonPath("$.totalPayment", is(63000.0)));
    }

    @Test
    void testDeletePayrollSuccess() throws Exception {
        Mockito.when(payrollService.deletePayroll(1L)).thenReturn(true);

        mockMvc.perform(delete("/payrolls/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Payroll deleted successfully"));
    }

    @Test
    void testDeletePayrollFail() throws Exception {
        Mockito.when(payrollService.deletePayroll(1L)).thenReturn(false);

        mockMvc.perform(delete("/payrolls/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting payroll"));
    }

    @Test
    void testSavePayrollsBatch() throws Exception {
        ArrayList<Payroll> inputPayrolls = new ArrayList<>();
        inputPayrolls.add(new Payroll(8L, employee, new Date(System.currentTimeMillis()), 40000.0, 2000.0, 42000.0, paymentMethod));
        inputPayrolls.add(new Payroll(12L, employee, new Date(System.currentTimeMillis()), 35000.0, 1500.0, 36500.0, paymentMethod));

        ArrayList<Payroll> savedPayrolls = new ArrayList<>();
        savedPayrolls.add(new Payroll(4L, employee, new Date(System.currentTimeMillis()), 40000.0, 2000.0, 42000.0, paymentMethod));
        savedPayrolls.add(new Payroll(5L, employee, new Date(System.currentTimeMillis()), 35000.0, 1500.0, 36500.0, paymentMethod));

        Mockito.when(payrollService.savePayrolls(Mockito.any(ArrayList.class))).thenReturn(savedPayrolls);

        mockMvc.perform(post("/payrolls/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputPayrolls)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(4)))
                .andExpect(jsonPath("$[0].baseSalary", is(40000.0)))
                .andExpect(jsonPath("$[0].bonuses", is(2000.0)))
                .andExpect(jsonPath("$[0].totalPayment", is(42000.0)))
                .andExpect(jsonPath("$[1].id", is(5)))
                .andExpect(jsonPath("$[1].baseSalary", is(35000.0)))
                .andExpect(jsonPath("$[1].bonuses", is(1500.0)))
                .andExpect(jsonPath("$[1].totalPayment", is(36500.0)));
    }

    
    
}
