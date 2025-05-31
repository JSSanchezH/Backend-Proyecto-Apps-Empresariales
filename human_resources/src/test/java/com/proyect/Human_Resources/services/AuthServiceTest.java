package com.proyect.Human_Resources.services;

import com.proyect.Human_Resources.dto.CompanyRegisterRequest;
import com.proyect.Human_Resources.dto.CompanyRegisterRequest.CompanyRequest;
import com.proyect.Human_Resources.dto.CompanyRegisterRequest.UserCompanyRequest;
import com.proyect.Human_Resources.models.Company;
import com.proyect.Human_Resources.models.UserCompany;
import com.proyect.Human_Resources.Repositories.ICompanyRepository;
import com.proyect.Human_Resources.Repositories.IUserCompanyRepository;
import com.proyect.Human_Resources.Exceptions.UserAlreadyExistException;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private ICompanyRepository companyRepository;

    @Mock
    private IUserCompanyRepository userCompanyRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserCompanyService userCompanyService;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private AuthService authService;

    private CompanyRequest companyRequest;
    private UserCompanyRequest userCompanyRequest;
    private CompanyRegisterRequest request;

    private Company company;
    private UserCompany userCompany;

    @BeforeEach
    void setUp() {
        // Datos de la entidad persistente
        company = new Company();
        company.setName("Test Company");
        company.setNit(123456789L);
        company.setEmail("test@company.com");

        userCompany = new UserCompany();
        userCompany.setUserName("testuser");
        userCompany.setPassword("password123");

        // Datos del DTO
        companyRequest = new CompanyRequest();
        companyRequest.setName("Test Company");
        companyRequest.setNit(123456789L);
        companyRequest.setEmail("test@company.com");

        userCompanyRequest = new UserCompanyRequest();
        userCompanyRequest.setUserName("testuser");
        userCompanyRequest.setPassword("password123");

        request = new CompanyRegisterRequest();
        request.setCompany(companyRequest);
        request.setUser(userCompanyRequest);

        authService.setUserCompanyService(userCompanyService);
    }

    @Test
    void testRegisterCompanyWithUserSuccess() {
        when(userCompanyRepository.findByUserName("testuser")).thenReturn(Optional.empty());
        when(companyRepository.save(any(Company.class))).thenReturn(company);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userCompanyService.generateApiKey()).thenReturn("apikey123");
        when(userCompanyRepository.save(any(UserCompany.class))).thenReturn(userCompany);

        String result = authService.registerCompanyWithUser(request);

        assertEquals("Company and user registered successfully", result);
        verify(userCompanyRepository).findByUserName("testuser");
        verify(companyRepository).save(any(Company.class));
        verify(userCompanyRepository).save(any(UserCompany.class));
    }

    @Test
    void testRegisterCompanyWithUserThrowsExceptionWhenUserExists() {
        when(userCompanyRepository.findByUserName("testuser")).thenReturn(Optional.of(userCompany));

        assertThrows(UserAlreadyExistException.class, () -> {
            authService.registerCompanyWithUser(request);
        });

        verify(userCompanyRepository).findByUserName("testuser");
        verify(companyRepository, never()).save(any(Company.class));
    }

    @Test
    void testGetAuthenticatedUser() {
        when(httpServletRequest.getAttribute("authenticatedUser")).thenReturn(userCompany);

        UserCompany result = authService.getAuthenticatedUser(httpServletRequest);

        assertEquals(userCompany, result);
        verify(httpServletRequest).getAttribute("authenticatedUser");
    }


@Test
void testLoginUserNotFound() {
    when(userCompanyRepository.findByUserName("nonexistent")).thenReturn(Optional.empty());

    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
        authService.login("nonexistent", "password");
    });

    assertEquals("User not found", exception.getMessage());
    verify(userCompanyRepository).findByUserName("nonexistent");
}

@Test
void testLoginInvalidPassword() {
    String wrongPassword = "wrongPassword";
    String encodedPassword = "encodedPassword";

    userCompany.setPassword(encodedPassword);

    when(userCompanyRepository.findByUserName("testuser")).thenReturn(Optional.of(userCompany));
    when(passwordEncoder.matches(wrongPassword, encodedPassword)).thenReturn(false);

    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
        authService.login("testuser", wrongPassword);
    });

    assertEquals("Invalid credentials", exception.getMessage());
    verify(userCompanyRepository).findByUserName("testuser");
    verify(passwordEncoder).matches(wrongPassword, encodedPassword);
}

}
