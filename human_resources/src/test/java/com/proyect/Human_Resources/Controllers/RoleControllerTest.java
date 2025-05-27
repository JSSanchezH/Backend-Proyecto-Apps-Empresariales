package com.proyect.Human_Resources.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.RoleController;
import com.proyect.Human_Resources.models.Role;
import com.proyect.Human_Resources.services.RoleService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = RoleController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllRoles() throws Exception {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, "Developer"));
        roles.add(new Role(2L, "Manager"));

        Mockito.when(roleService.getRoles()).thenReturn(roles);

        mockMvc.perform(get("/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Developer")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Manager")));
    }

    @Test
    void testGetRoleById() throws Exception {
        Role role = new Role(1L, "Developer");

        Mockito.when(roleService.getRoleById(1L)).thenReturn(Optional.of(role));

        mockMvc.perform(get("/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Developer")));
    }

    @Test
    void testGetRoleByIdNotFound() throws Exception {
        Mockito.when(roleService.getRoleById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/roles/999"))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveRole() throws Exception {
        Role input = new Role(null, "Analyst");
        Role saved = new Role(3L, "Analyst");

        Mockito.when(roleService.saveRole(Mockito.any(Role.class))).thenReturn(saved);

        mockMvc.perform(post("/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Analyst")));
    }

    @Test
    void testUpdateRole() throws Exception {
        Role updated = new Role(1L, "Senior Developer");

        Mockito.when(roleService.updateRole(Mockito.any(Role.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/roles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Senior Developer")));
    }

    @Test
    void testDeleteRoleSuccess() throws Exception {
        Mockito.when(roleService.deleteRole(1L)).thenReturn(true);

        mockMvc.perform(delete("/roles/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Role deleted successfully"));
    }

    @Test
    void testDeleteRoleFail() throws Exception {
        Mockito.when(roleService.deleteRole(1L)).thenReturn(false);

        mockMvc.perform(delete("/roles/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting role"));
    }

    @Test
    void testSaveRolesBatch() throws Exception {
        ArrayList<Role> inputRoles = new ArrayList<>();
        inputRoles.add(new Role(null, "Team Lead"));
        inputRoles.add(new Role(null, "QA Engineer"));

        ArrayList<Role> savedRoles = new ArrayList<>();
        savedRoles.add(new Role(4L, "Team Lead"));
        savedRoles.add(new Role(5L, "QA Engineer"));

        Mockito.when(roleService.saveRoles(Mockito.any(ArrayList.class))).thenReturn(savedRoles);

        mockMvc.perform(post("/roles/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputRoles)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(4)))
                .andExpect(jsonPath("$[0].name", is("Team Lead")))
                .andExpect(jsonPath("$[1].id", is(5)))
                .andExpect(jsonPath("$[1].name", is("QA Engineer")));
    }
    
}
