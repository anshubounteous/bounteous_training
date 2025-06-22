package com.example.service_provider_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private String category;
    private String description;
    private String location;
    private LocalDate preferredDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private Long assignedProviderId;
}
