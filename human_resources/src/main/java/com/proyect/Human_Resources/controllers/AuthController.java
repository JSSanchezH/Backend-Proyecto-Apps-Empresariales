package com.proyect.Human_Resources.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.Human_Resources.dto.CompanyRegisterRequest;
import com.proyect.Human_Resources.dto.CompanyLoginRequest;

import com.proyect.Human_Resources.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCompanyWithUser(@RequestBody CompanyRegisterRequest request) {
        try {
            String result = authService.registerCompanyWithUser(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CompanyLoginRequest request) {
        try {
            String apiKey = authService.login(request.getUserName(), request.getPassword());
            return ResponseEntity.ok(apiKey);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}