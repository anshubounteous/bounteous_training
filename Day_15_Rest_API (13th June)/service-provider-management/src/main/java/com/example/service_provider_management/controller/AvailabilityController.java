package com.example.service_provider_management.controller;

import com.example.service_provider_management.calendar.AvailabilityService;
import com.example.service_provider_management.model.Availability;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @PostMapping
    public ResponseEntity<Availability> addAvailability(@RequestBody Availability availability) {
        return ResponseEntity.ok(availabilityService.setAvailability(availability));
    }

    @GetMapping("/{providerId}")
    public ResponseEntity<List<Availability>> getAvailability(@PathVariable Long providerId) {
        return ResponseEntity.ok(availabilityService.getByProvider(providerId));
    }
}
