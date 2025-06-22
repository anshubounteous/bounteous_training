package com.example.service_provider_management.controller;

import com.example.service_provider_management.model.ServiceRequest;
import com.example.service_provider_management.service.ServiceRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    @PostMapping
    public ResponseEntity<ServiceRequest> createRequest(@RequestBody ServiceRequest request) {
        return ResponseEntity.ok(serviceRequestService.createRequest(request));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ServiceRequest>> getRequestsForCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(serviceRequestService.getByCustomerId(customerId));
    }
}
