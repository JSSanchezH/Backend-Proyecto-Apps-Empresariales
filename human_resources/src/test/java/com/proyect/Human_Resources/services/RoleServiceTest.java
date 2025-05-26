package com.proyect.Human_Resources.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyect.Human_Resources.Repositories.IRoleReposiotry;
import com.proyect.Human_Resources.models.Role;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private IRoleReposiotry roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Role role;
    private ArrayList<Role> roleList;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(1L);
        role.setName("Admin");

        roleList = new ArrayList<>();
        roleList.add(role);
    }

    @Test
    void testGetRoles() {
        when(roleRepository.findAll()).thenReturn(roleList);

        ArrayList<Role> result = roleService.getRoles();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(roleRepository).findAll();
    }

    @Test
    void testSaveRole() {
        when(roleRepository.save(role)).thenReturn(role);

        Role saved = roleService.saveRole(role);

        assertNotNull(saved);
        assertEquals("Admin", saved.getName());
        verify(roleRepository).save(role);
    }

    @Test
    void testSaveRoles() {
        when(roleRepository.saveAll(roleList)).thenReturn(roleList);

        ArrayList<Role> savedList = roleService.saveRoles(roleList);

        assertNotNull(savedList);
        assertEquals(1, savedList.size());
        verify(roleRepository).saveAll(roleList);
    }

    @Test
    void testGetRoleById_Found() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Optional<Role> found = roleService.getRoleById(1L);

        assertTrue(found.isPresent());
        assertEquals("Admin", found.get().getName());
        verify(roleRepository).findById(1L);
    }

    @Test
    void testGetRoleById_NotFound() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Role> found = roleService.getRoleById(1L);

        assertFalse(found.isPresent());
        verify(roleRepository).findById(1L);
    }

    @Test
    void testUpdateRole() {
        Role updatedData = new Role();
        updatedData.setName("User");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(roleRepository.save(any(Role.class))).thenAnswer(inv -> inv.getArgument(0));

        Role updated = roleService.updateRole(updatedData, 1L);

        assertEquals("User", updated.getName());
        verify(roleRepository).findById(1L);
        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void testDeleteRole_Success() {
        doNothing().when(roleRepository).deleteById(1L);

        boolean result = roleService.deleteRole(1L);

        assertTrue(result);
        verify(roleRepository).deleteById(1L);
    }

    @Test
    void testDeleteRole_Failure() {
        doThrow(new RuntimeException("Delete failed")).when(roleRepository).deleteById(1L);

        boolean result = roleService.deleteRole(1L);

        assertFalse(result);
        verify(roleRepository).deleteById(1L);
    }
    
}
