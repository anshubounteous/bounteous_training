package com.example.service_provider_management.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
