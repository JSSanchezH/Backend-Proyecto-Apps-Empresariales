package com.proyect.Human_Resources.Controllers;


    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
    import com.proyect.Human_Resources.controllers.DepartmentController;
    import com.proyect.Human_Resources.models.City;
    import com.proyect.Human_Resources.models.Company;
    import com.proyect.Human_Resources.models.Continent;
    import com.proyect.Human_Resources.models.Country;
    import com.proyect.Human_Resources.models.Department;
    import com.proyect.Human_Resources.models.Headquarter;
    import com.proyect.Human_Resources.models.State;
    import com.proyect.Human_Resources.models.UserCompany;
    import com.proyect.Human_Resources.services.AuthService;
    import com.proyect.Human_Resources.services.DepartmentService;

    import jakarta.servlet.http.HttpServletRequest;

    import org.junit.jupiter.api.Test;
    import org.mockito.Mockito;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.context.annotation.ComponentScan.Filter;
    import org.springframework.context.annotation.FilterType;
    import org.springframework.http.MediaType;
    import org.springframework.test.web.servlet.MockMvc;


    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Optional;

    import static org.hamcrest.Matchers.is;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = DepartmentController.class,
    excludeFilters = {
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

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
    private final UserCompany userCompany = new UserCompany(1L, company, "123", "123", "123asd");

    @Test
    void testGetAllDepartments() throws Exception {
        Department dept1 = new Department(1L, "Human Resources", headquarter);
        Department dept2 = new Department(2L, "Information Technology", headquarter);

        Mockito.when(authService.getAuthenticatedUser(Mockito.any(HttpServletRequest.class))).thenReturn(userCompany);
        Mockito.when(departmentService.getDepartmentsByCompanyNit(123456789L))
                .thenReturn(new ArrayList<>(Arrays.asList(dept1, dept2)));

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Human Resources")))
                .andExpect(jsonPath("$[0].headquarter.name", is("Main Office")))
                .andExpect(jsonPath("$[1].name", is("Information Technology")));
    }

    @Test
    void testGetDepartmentById() throws Exception {
        Department department = new Department(1L, "Finance", headquarter);
        Mockito.when(departmentService.getDepartmentById(1L)).thenReturn(Optional.of(department));

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Finance")))
                .andExpect(jsonPath("$.headquarter.name", is("Main Office")));
    }

    @Test
    void testSaveDepartment() throws Exception {
        Department input = new Department(null, "Marketing", headquarter);
        Department saved = new Department(3L, "Marketing", headquarter);

        Mockito.when(departmentService.saveDepartment(Mockito.any(Department.class))).thenReturn(saved);

        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Marketing")))
                .andExpect(jsonPath("$.headquarter.name", is("Main Office")));
    }

    @Test
    void testUpdateDepartment() throws Exception {
        Department updated = new Department(1L, "Sales", headquarter);

        Mockito.when(departmentService.updateDepartment(Mockito.any(Department.class), Mockito.eq(1L)))
                .thenReturn(updated);

        mockMvc.perform(put("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Sales")))
                .andExpect(jsonPath("$.headquarter.name", is("Main Office")));
    }

    @Test
    void testDeleteDepartmentSuccess() throws Exception {
        Mockito.when(departmentService.deleteDepartment(1L)).thenReturn(true);

        mockMvc.perform(delete("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Department deleted successfully"));
    }

    @Test
    void testDeleteDepartmentFail() throws Exception {
        Mockito.when(departmentService.deleteDepartment(1L)).thenReturn(false);

        mockMvc.perform(delete("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting department"));
    }

    @Test
    void testSaveDepartmentsBatch() throws Exception {
        Department input1 = new Department(null, "Operations", headquarter);
        Department input2 = new Department(null, "Research", headquarter);

        Department saved1 = new Department(1L, "Operations", headquarter);
        Department saved2 = new Department(2L, "Research", headquarter);

        Mockito.when(departmentService.saveDepartments(Mockito.any()))
                .thenReturn(new ArrayList<>(Arrays.asList(saved1, saved2)));

        mockMvc.perform(post("/departments/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Arrays.asList(input1, input2))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Operations")))
                .andExpect(jsonPath("$[0].headquarter.name", is("Main Office")))
                .andExpect(jsonPath("$[1].name", is("Research")));
    }
    
}
