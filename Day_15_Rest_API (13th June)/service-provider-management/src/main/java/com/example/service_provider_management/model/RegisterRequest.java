package com.example.service_provider_management.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;  // CUSTOMER or PROVIDER
}
