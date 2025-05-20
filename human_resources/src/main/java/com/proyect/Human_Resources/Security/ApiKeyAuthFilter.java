package com.proyect.Human_Resources.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.proyect.Human_Resources.Repositories.IUserCompanyRepository;
import com.proyect.Human_Resources.models.UserCompany;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Indicates that this class is a Spring component
public class ApiKeyAuthFilter extends OncePerRequestFilter { // Extends OncePerRequestFilter to ensure the filter is
                                                             // executed once per request

    @Autowired
    private IUserCompanyRepository userCompanyRepository; // Injecting the IUserCompanyRepository dependency

    private static final List<String> PUBLIC_PATHS = new ArrayList<String>(Arrays.asList(
            "/continents",
            "/countries",
            "/states",
            "/cities",
            "/companies",
            "/UserCompany",
            "/absence_types",
            "/payment-method",
            "/roles"));

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Check if the request path is public

        boolean isPublic = PUBLIC_PATHS.stream().anyMatch(path::startsWith);
        if (isPublic) {
            // If the path is public, continue with the filter chain
            filterChain.doFilter(request, response);
            return;     
            
        }

        String apiKey = request.getHeader("X-API-KEY");

        if (apiKey == null || apiKey.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("API key is missing");
            return;
        }

        UserCompany userCompany = userCompanyRepository.findByApiKey(apiKey);

        if (userCompany == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid API key");
            return;
        }

        // Set the authenticated user in the request attributes for further processing
        request.setAttribute("authenticatedUser", userCompany);

        // If the API key is valid, continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
