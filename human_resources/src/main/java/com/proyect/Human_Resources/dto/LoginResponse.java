package com.proyect.Human_Resources.dto;
public class LoginResponse {
    private String apiKey;
    private Long id;

    public LoginResponse(String apiKey, Long id) {
        this.apiKey = apiKey;
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Long getId() {
        return id;
    }
}
