package com.example.service_provider_management.repository;

import com.example.service_provider_management.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByProviderId(Long providerId);

    List<Availability> findByCategoryAndAvailableDateAndIsAvailable(String category, LocalDate date, Boolean isAvailable);
}
