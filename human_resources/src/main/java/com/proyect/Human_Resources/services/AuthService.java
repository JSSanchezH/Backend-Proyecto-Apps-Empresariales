package com.proyect.Human_Resources.services;

import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.models.UserCompany;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthService {

    public UserCompany getAuthenticatedUser(HttpServletRequest request) {
        return (UserCompany) request.getAttribute("authenticatedUser");
    }
    
}
