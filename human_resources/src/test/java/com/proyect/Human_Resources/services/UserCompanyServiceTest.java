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

import com.proyect.Human_Resources.Repositories.IUserCompanyRepository;
import com.proyect.Human_Resources.models.UserCompany;
import com.proyect.Human_Resources.models.Company;
import com.proyect.Human_Resources.Exceptions.UserAlreadyExistException;

@ExtendWith(MockitoExtension.class)
public class UserCompanyServiceTest {
    
    @Mock
    private IUserCompanyRepository userCompanyRepository;

    @InjectMocks
    private UserCompanyService userCompanyService;

    private UserCompany userCompany;
    private Company company;
    private ArrayList<UserCompany> userCompanies;

    @BeforeEach
    void setUp() {
        company = new Company();
        company.setId(1L);
        company.setName("Test Company");

        userCompany = new UserCompany();
        userCompany.setId(1L);
        userCompany.setUserName("testuser");
        userCompany.setPassword("password123");
        userCompany.setCompany(company);
        userCompany.setApiKey("test-api-key");

        userCompanies = new ArrayList<>();
        userCompanies.add(userCompany);
    }

    @Test
    void testGetUserCompanies() {
        when(userCompanyRepository.findAll()).thenReturn(userCompanies);

        ArrayList<UserCompany> result = userCompanyService.getUserCompanies();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testuser", result.get(0).getUserName());
        verify(userCompanyRepository).findAll();
    }

    @Test
    void testSaveUserCompany() {
        when(userCompanyRepository.findByUserName("testuser")).thenReturn(Optional.empty());
        when(userCompanyRepository.save(any(UserCompany.class))).thenReturn(userCompany);

        UserCompany result = userCompanyService.saveUserCompany(userCompany);

        assertNotNull(result);
        assertEquals("testuser", result.getUserName());
        assertNotNull(result.getApiKey());
        verify(userCompanyRepository).findByUserName("testuser");
        verify(userCompanyRepository).save(userCompany);
    }

    @Test
    void testSaveUserCompanyThrowsExceptionWhenUserExists() {
        when(userCompanyRepository.findByUserName("testuser")).thenReturn(Optional.of(userCompany));

        assertThrows(UserAlreadyExistException.class, () -> {
            userCompanyService.saveUserCompany(userCompany);
        });

        verify(userCompanyRepository).findByUserName("testuser");
        verify(userCompanyRepository, never()).save(any(UserCompany.class));
    }

    @Test
    void testSaveUserCompanies() {
        when(userCompanyRepository.saveAll(userCompanies)).thenReturn(userCompanies);

        ArrayList<UserCompany> result = userCompanyService.saveUserCompanies(userCompanies);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userCompanyRepository).saveAll(userCompanies);
    }

    @Test
    void testGetUserCompanyById() {
        when(userCompanyRepository.findById(1L)).thenReturn(Optional.of(userCompany));

        Optional<UserCompany> result = userCompanyService.getUserCompanyById(1L);

        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUserName());
        verify(userCompanyRepository).findById(1L);
    }

    @Test
    void testUpdateUserCompany() {
        UserCompany updatedUserCompany = new UserCompany();
        updatedUserCompany.setUserName("updateduser");
        updatedUserCompany.setPassword("newpassword");
        updatedUserCompany.setCompany(company);

        when(userCompanyRepository.findById(1L)).thenReturn(Optional.of(userCompany));
        when(userCompanyRepository.save(any(UserCompany.class))).thenReturn(userCompany);

        UserCompany result = userCompanyService.updateUserCompany(updatedUserCompany, 1L);

        assertNotNull(result);
        verify(userCompanyRepository).findById(1L);
        verify(userCompanyRepository).save(userCompany);
    }

    @Test
    void testDeleteUserCompanySuccess() {
        doNothing().when(userCompanyRepository).deleteById(1L);

        boolean result = userCompanyService.deleteUserCompany(1L);

        assertTrue(result);
        verify(userCompanyRepository).deleteById(1L);
    }

    @Test
    void testDeleteUserCompanyFailure() {
        doThrow(new RuntimeException()).when(userCompanyRepository).deleteById(1L);

        boolean result = userCompanyService.deleteUserCompany(1L);

        assertFalse(result);
        verify(userCompanyRepository).deleteById(1L);
    }

    @Test
    void testGenerateApiKeyLength() {
        UserCompany newUserCompany = new UserCompany();
        newUserCompany.setUserName("newuser");
        newUserCompany.setPassword("password");
        newUserCompany.setCompany(company);

        when(userCompanyRepository.findByUserName("newuser")).thenReturn(Optional.empty());
        when(userCompanyRepository.save(any(UserCompany.class))).thenAnswer(invocation -> {
            UserCompany saved = invocation.getArgument(0);
            return saved;
        });

        UserCompany result = userCompanyService.saveUserCompany(newUserCompany);

        assertNotNull(result.getApiKey());
        assertEquals(40, result.getApiKey().length());
    }
}
